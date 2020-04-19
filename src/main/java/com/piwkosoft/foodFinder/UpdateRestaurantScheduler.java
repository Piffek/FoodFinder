package com.piwkosoft.foodFinder;

import com.piwkosoft.foodFinder.Core.Constranits;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.CountryFacade;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceTypeFacade;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Dto.CountryDTO;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import com.piwkosoft.foodFinder.WebServices.CustomJson;
import com.piwkosoft.foodFinder.WebServices.JsonStrategy;
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson;
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson.JsonPlace;
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson.JsonPlace.PlaceList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class UpdateRestaurantScheduler {

  private static final Logger logger = LoggerFactory.getLogger(UpdateRestaurantScheduler.class);

  private final PlaceFacade placeFacade;
  private final CountryFacade countryFacade;
  private final PlaceTypeFacade placeTypeFacade;
  private final JsonStrategy jsonStrategy;
  private final RestTemplate restTemplate;
  private final CustomJson json;


  public UpdateRestaurantScheduler(
      final PlaceFacade placeFacade,
      final CountryFacade countryFacade,
      final PlaceTypeFacade placeTypeFacade,
      final JsonStrategy jsonStrategy, RestTemplate restTemplate) {
    this.placeFacade = placeFacade;
    this.countryFacade = countryFacade;
    this.placeTypeFacade = placeTypeFacade;
    this.jsonStrategy = jsonStrategy;
    this.restTemplate = restTemplate;

    this.jsonStrategy.setJsonStrategy(new PlaceJson(restTemplate));
    this.json = this.jsonStrategy.json();
  }

  //TODO in many thread
  @Scheduled(cron = Constranits.RESTAURANT_DOWNLOAD_CRON)
  public void updateRestaurant() {

    final List<String> cities = countryFacade.getAllCountries()
        .stream()
        .map(CountryDTO::getName)
        .collect(Collectors.toList());

    cities
        .forEach(city -> this.createAndPaging(PlaceJson.BASE_URL + city));
  }

  private synchronized void createAndPaging(final String apiUrl) {
    final PlaceList placeList = createRestaurantWithPlacesFromJson(
        apiUrl);

    String nextPageToken = placeList.getNextPageToken();

    while (json.hasNextPage(nextPageToken)) {
      try {
        wait(5000);
      } catch (InterruptedException e) {
        logger.error("waiting error", e);
      }

      final PlaceList restaurants = createRestaurantWithPlacesFromJson(
          apiUrl + "&pagetoken=" + nextPageToken);

      nextPageToken = json.returnNextPageToken(restaurants);
    }
  }

  public PlaceList createRestaurantWithPlacesFromJson(final String url) {
    final PlaceList placeList =
        (PlaceList) json.objectFromJson(url);
    createPlaceType(placeList.getResults());
    createRestaurants(placeList.getResults());
    return placeList;
  }

  private void createPlaceType(final JsonPlace[] restaurantDTOS) {
    Arrays.stream(restaurantDTOS)
        .filter(Objects::nonNull)
        .flatMap(restaurant -> Stream.of(restaurant.getTypes()))
        .collect(Collectors.toSet())
        .stream()
        .map(type -> new PlaceTypeDTO().setName(type))
        .forEach(placeTypeFacade::createIfNotExist);
    logger.info("create Place Types");
  }

  private void createRestaurants(final JsonPlace[] restaurantDTOS) {
    final List<PlaceDTO> restaurants = Arrays.stream(restaurantDTOS)
        .filter(Objects::nonNull)
        .map(restaurant ->
            new PlaceDTO()
                .setFormattedAddress(restaurant.getFormattedAddress())
                .setUserRatingsTotal(restaurant.getUserRatingsTotal())
                .setIcon(restaurant.getIcon())
                .setName(restaurant.getName())
                .setOpen(restaurant.isOpen())
                .setRating(restaurant.getRating())
                .setTypes(
                    placeTypeFacade.findTypesByName(Arrays.asList(restaurant.getTypes()))
                        .stream()
                        .map(PlaceTypeDTO::getId)
                        .collect(Collectors.toSet()))
        )
        .collect(Collectors.toList());

    placeFacade.createOrUpdate(restaurants);
    logger.info("create restaurants");
  }
}