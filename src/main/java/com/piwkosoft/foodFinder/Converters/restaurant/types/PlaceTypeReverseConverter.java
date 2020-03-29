package com.piwkosoft.foodFinder.Converters.restaurant.types;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RestaurantService;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Configuration;
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
public class PlaceTypeReverseConverter implements ReverseConverter<PlaceTypeEntity, PlaceTypeDTO>
{

  private final RestaurantService restaurantService;

  public PlaceTypeReverseConverter(
      final RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @Override
  public PlaceTypeEntity convert(final PlaceTypeDTO placeTypeDTO, final PlaceTypeEntity placeTypeEntity) {

    return placeTypeEntity
        .setName(placeTypeDTO.getName());
  }
}
