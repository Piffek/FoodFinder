package com.piwkosoft.foodFinder.Converters.place.types;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import org.springframework.stereotype.Component;

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
public class PlaceTypeConverter implements Converter<PlaceTypeDTO, PlaceTypeEntity> {

  @Override
  public PlaceTypeDTO convert(final PlaceTypeEntity placeTypeEntity) {
    return PlaceTypeDTO.builder()
        .id(placeTypeEntity.getId())
        .name(placeTypeEntity.getName())
        .build();
  }
}
