package com.piwkosoft.foodFinder;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Core.Constranits;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceTypeFacade;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.RestaurantFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import com.piwkosoft.foodFinder.Dto.RestaurantDTO;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
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

  private final RestaurantFacade restaurantFacade;
  private final PlaceTypeFacade placeTypeFacade;
  private final RestTemplate restTemplate;
  private final ReverseConverter<PlaceTypeEntity, PlaceTypeDTO> reverseConverter;

  public UpdateRestaurantScheduler(
      final RestaurantFacade restaurantFacade,
      final PlaceTypeFacade placeTypeFacade,
      final RestTemplate restTemplate,
      final ReverseConverter<PlaceTypeEntity, PlaceTypeDTO> reverseConverter) {
    this.restaurantFacade = restaurantFacade;
    this.placeTypeFacade = placeTypeFacade;
    this.restTemplate = restTemplate;
    this.reverseConverter = reverseConverter;
  }


  @Scheduled(cron = Constranits.RESTAURANT_DOWNLOAD_CRON)
  public void updateRestaurant() {
    JsonRestaurant.RestaurantList restaurantList = restTemplate.getForObject(
        Constranits.RESTAURANT_API_URL,
        JsonRestaurant.RestaurantList .class
    );

    JsonRestaurant[] restaurantDTOS = restaurantList.getResults();

    createPlaceType(restaurantDTOS);
    createRestaurants(restaurantDTOS);
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class JsonRestaurant {
    private String name;
    private String[] types;
    private String formattedAddress;
    private String icon;
    private boolean isOpen;
    private Double rating;
    private BigDecimal userRatingsTotal;

    @Getter
    @Setter
    public static class RestaurantList {
      private JsonRestaurant[] results;
    }
  }

  private synchronized void createPlaceType(final JsonRestaurant[] restaurantDTOS) {
    Arrays.stream(restaurantDTOS)
        .filter(Objects::nonNull)
        .flatMap(restaurant -> Stream.of(restaurant.types))
        .collect(Collectors.toSet())
        .stream()
        .map(type -> new PlaceTypeDTO().setName(type))
        .forEach(placeTypeFacade::createIfNotExist);

    logger.info("added Place Types");
  }

  private synchronized void createRestaurants(final JsonRestaurant[] restaurantDTOS) {
    final List<RestaurantDTO> restaurants =  Arrays.stream(restaurantDTOS)
        .filter(Objects::nonNull)
        .map(restaurant ->
            new RestaurantDTO()
                .setFormattedAddress(restaurant.getFormattedAddress())
                .setUserRatingsTotal(restaurant.getUserRatingsTotal())
                .setIcon(restaurant.getIcon())
                .setName(restaurant.getName())
                .setOpen(restaurant.isOpen)
                .setRating(restaurant.getRating())
                .setTypes(
                    placeTypeFacade.findTypesByName(Arrays.asList(restaurant.getTypes()))
                    .stream()
                    .map(PlaceTypeDTO::getId)
                    .collect(Collectors.toSet()))
        )
        .collect(Collectors.toList());

    restaurantFacade.createOrUpdate(restaurants);
    logger.info("create restaurants");
  }
}