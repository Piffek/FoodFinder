package com.piwkosoft.foodFinder.Converters.restaurant;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Dto.RestaurantDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
public class RestaurantConverter implements Converter<RestaurantDTO, RestaurantEntity> {

  @Override
  public RestaurantDTO convert(final RestaurantEntity restaurantEntity) {
    final Set<Long> typesId = restaurantEntity.getTypes().stream()
        .filter(Objects::nonNull)
        .map(PlaceTypeEntity::getId)
        .collect(Collectors.toSet());

    return new RestaurantDTO()
        .setFormattedAddress(restaurantEntity.getFormattedAdress())
        .setName(restaurantEntity.getName())
        .setTypes(typesId)
        .setOpen(restaurantEntity.isOpen())
        .setIcon(restaurantEntity.getIcon())
        .setRating(restaurantEntity.getRating())
        .setUserRatingsTotal(restaurantEntity.getUserRatingsTotal());
  }
}
