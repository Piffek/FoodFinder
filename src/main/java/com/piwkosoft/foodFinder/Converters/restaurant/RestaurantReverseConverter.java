package com.piwkosoft.foodFinder.Converters.restaurant;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceTypeService;
import com.piwkosoft.foodFinder.Dto.RestaurantDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
public class RestaurantReverseConverter implements
    ReverseConverter<RestaurantEntity, RestaurantDTO> {

  private final PlaceTypeService placeTypeService;

  public RestaurantReverseConverter(
      final PlaceTypeService placeTypeService) {
    this.placeTypeService = placeTypeService;
  }

  @Override
  public RestaurantEntity convert(final RestaurantDTO restaurantDTO, final RestaurantEntity restaurantEntity) {
    final Set<PlaceTypeEntity> placeTypeEntities =
        restaurantDTO.getTypes().stream()
        .filter(Objects::nonNull)
        .map(placeTypeService::findById)
        .collect(Collectors.toSet());

    return restaurantEntity
        .setFormattedAdress(restaurantDTO.getFormattedAddress())
        .setName(restaurantDTO.getName())
        .setTypes(placeTypeEntities)
        .setOpen(restaurantDTO.isOpen())
        .setIcon(restaurantDTO.getIcon())
        .setRating(restaurantDTO.getRating())
        .setUserRatingsTotal(restaurantDTO.getUserRatingsTotal());
  }
}
