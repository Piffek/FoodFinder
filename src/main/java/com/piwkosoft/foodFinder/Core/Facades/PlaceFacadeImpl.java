package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RestaurantService;
import java.util.List;
import java.util.Objects;
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
public class PlaceFacadeImpl implements PlaceFacade {

  private final ReverseConverter<PlaceEntity, PlaceDTO> reverseConverter;

  private final RestaurantService restaurantService;

  public PlaceFacadeImpl(
      final ReverseConverter<PlaceEntity, PlaceDTO> reverseConverter,
      final RestaurantService restaurantService) {
    this.reverseConverter = reverseConverter;
    this.restaurantService = restaurantService;
  }

  @Override
  public void createOrUpdate(final List<PlaceDTO> placeDTOS) {
    placeDTOS.stream()
        .map(restaurant -> restaurantService
            .findByNameAndAdress(restaurant.getName(), restaurant.getFormattedAddress()))
        .filter(Objects::nonNull)
        .forEach(restaurantService::update);

    placeDTOS.stream()
        .filter(restaurant -> restaurantService
            .findByNameAndAdress(restaurant.getName(), restaurant.getFormattedAddress()) == null)
        .map(restaurant -> reverseConverter.convert(restaurant, new PlaceEntity()))
        .forEach(restaurantService::create);
  }
}
