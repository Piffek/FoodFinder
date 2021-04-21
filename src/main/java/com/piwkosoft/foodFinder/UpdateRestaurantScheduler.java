package com.piwkosoft.foodFinder;

import com.piwkosoft.foodFinder.Core.Facades.Interfaces.CountryFacade;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceTypeFacade;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Dto.CountryDTO;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import com.piwkosoft.foodFinder.WebServices.CustomJson;
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson;
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson.JsonPlace;
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson.JsonPlace.JsonPlaceList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
@Scope("prototype")
public class UpdateRestaurantScheduler {

  private static final Logger logger = LoggerFactory.getLogger(UpdateRestaurantScheduler.class);

  private static final String PAGE_TOKEN_URL_KEY = "&pagetoken=";

  private final PlaceFacade placeFacade;
  private final CountryFacade countryFacade;
  private final PlaceTypeFacade placeTypeFacade;
  private final RestTemplate restTemplate;

  public UpdateRestaurantScheduler(
      final PlaceFacade placeFacade,
      final CountryFacade countryFacade,
      final PlaceTypeFacade placeTypeFacade,
      final RestTemplate restTemplate) {
    this.placeFacade = placeFacade;
    this.countryFacade = countryFacade;
    this.placeTypeFacade = placeTypeFacade;
    this.restTemplate = restTemplate;
  }

  @Scheduled(cron = "${cronjob.restaurant.download.scheduled.time}")
  public void updateRestaurant() {
    final CustomJson json = new PlaceJson(restTemplate);

    logger.info("inserting restaurants for url starting...");

    countryFacade.getAllCountries()
        .stream()
        .map(CountryDTO::getName)
        .forEach(country -> this.createAndPaging(String.format("%s%s", PlaceJson.BASE_URL, country), json));

    logger.info("inserting restaurants for url ending...");
  }

  public synchronized List<JsonPlaceList> createAndPaging(final String apiUrl, final CustomJson json) {
    final JsonPlaceList jsonPlaceList = createRestaurantWithPlaces(apiUrl, json);
    String nextPageToken = jsonPlaceList.getNextPageToken();

    final List<JsonPlaceList> placesPerPage = new ArrayList<>();
    placesPerPage.add(jsonPlaceList);

    while (nextPageToken != null) {
      try {
        wait(5000);
      } catch (final InterruptedException e) {
        logger.error("waiting error", e);
      }

      final String placeUrl = String.format("%s%s%s", apiUrl, PAGE_TOKEN_URL_KEY, nextPageToken);
      final JsonPlaceList restaurants = createRestaurantWithPlaces(placeUrl, json);

      nextPageToken = restaurants.getNextPageToken();
      placesPerPage.add(restaurants);
    }

    return placesPerPage;
  }

  private synchronized JsonPlaceList createRestaurantWithPlaces(final String url, final CustomJson json) {
    final JsonPlaceList jsonPlaceListByUrl = (JsonPlaceList) json.objectFromJson(url);
    createPlaceType(jsonPlaceListByUrl.getResults());
    createRestaurants(jsonPlaceListByUrl.getResults());
    return jsonPlaceListByUrl;
  }

  private synchronized void createPlaceType(final JsonPlace[] restaurantDTOS) {
    Arrays.stream(restaurantDTOS)
        .filter(Objects::nonNull)
        .flatMap(restaurant -> Stream.of(restaurant.getTypes()))
        .collect(Collectors.toSet())
        .stream()
        .map(type -> new PlaceTypeDTO().setName(type))
        .forEach(placeTypeFacade::createIfNotExist);
  }

  private synchronized void createRestaurants(final JsonPlace[] restaurantDTOS) {
    Arrays.stream(restaurantDTOS)
        .filter(Objects::nonNull)
        .map(this::create)
        .forEach(placeFacade::createOrUpdate);
  }

  private synchronized PlaceDTO create(final JsonPlace json) {
    final Set<Long> placeTypeIds =
        placeTypeFacade.findTypesByName(Arrays.asList(json.getTypes()))
            .stream()
            .filter(Objects::nonNull)
            .map(PlaceTypeDTO::getId)
            .collect(Collectors.toSet());

    return new PlaceDTO()
        .setFormattedAddress(json.getFormattedAddress())
        .setUserRatingsTotal(json.getUserRatingsTotal())
        .setIcon(json.getIcon())
        .setName(json.getName())
        .setOpen(json.isOpen())
        .setRating(json.getRating())
        .setTypes(placeTypeIds);
  }
}