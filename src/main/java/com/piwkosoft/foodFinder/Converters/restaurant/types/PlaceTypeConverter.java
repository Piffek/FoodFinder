package com.piwkosoft.foodFinder.Converters.restaurant.types;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
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
public class PlaceTypeConverter implements Converter<PlaceTypeDTO, PlaceTypeEntity> {

  @Override
  public PlaceTypeDTO convert(final PlaceTypeEntity placeTypeEntity) {
    return new PlaceTypeDTO()
        .setId(placeTypeEntity.getId())
        .setName(placeTypeEntity.getName());
  }
}
