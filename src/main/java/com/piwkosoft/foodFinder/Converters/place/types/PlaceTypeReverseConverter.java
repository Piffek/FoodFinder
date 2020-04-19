package com.piwkosoft.foodFinder.Converters.place.types;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceService;
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
public class PlaceTypeReverseConverter implements ReverseConverter<PlaceTypeEntity, PlaceTypeDTO>
{

  private final PlaceService placeService;

  public PlaceTypeReverseConverter(
      final PlaceService placeService) {
    this.placeService = placeService;
  }

  @Override
  public PlaceTypeEntity convert(final PlaceTypeDTO placeTypeDTO, final PlaceTypeEntity placeTypeEntity) {

    return placeTypeEntity
        .setName(placeTypeDTO.getName());
  }
}
