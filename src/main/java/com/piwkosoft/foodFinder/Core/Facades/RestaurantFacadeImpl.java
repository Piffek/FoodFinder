package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceTypeService;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import com.piwkosoft.foodFinder.Dto.RestaurantDTO;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.RestaurantFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RestaurantService;
import com.piwkosoft.foodFinder.UpdateRestaurantScheduler.JsonRestaurant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

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
public class RestaurantFacadeImpl implements RestaurantFacade {

  private final ReverseConverter<RestaurantEntity, RestaurantDTO> reverseConverter;

  private final RestaurantService restaurantService;

  public RestaurantFacadeImpl(
      final ReverseConverter<RestaurantEntity, RestaurantDTO> reverseConverter,
      final RestaurantService restaurantService) {
    this.reverseConverter = reverseConverter;
    this.restaurantService = restaurantService;
  }

  @Override
  public void createOrUpdate(final List<RestaurantDTO> restaurantDTOs) {
    restaurantDTOs.stream()
        .map(restaurant -> reverseConverter.convert(restaurant, new RestaurantEntity()))
        .forEach(restaurantService::createOrUpdate);
  }
}
