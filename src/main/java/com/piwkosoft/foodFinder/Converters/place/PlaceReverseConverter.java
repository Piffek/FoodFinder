package com.piwkosoft.foodFinder.Converters.place;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceTypeService;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
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
public class PlaceReverseConverter implements ReverseConverter<PlaceEntity, PlaceDTO> {

  private final PlaceTypeService placeTypeService;

  public PlaceReverseConverter(final PlaceTypeService placeTypeService) {
    this.placeTypeService = placeTypeService;
  }

  @Override
  public PlaceEntity convert(final PlaceDTO placeDTO, final PlaceEntity placeEntity) {
    final Set<PlaceTypeEntity> placeTypeEntities =
        placeDTO.getTypes().stream()
            .filter(Objects::nonNull)
            .map(placeTypeService::findById)
            .collect(Collectors.toSet());

    return placeEntity
        .setFormattedAdress(placeDTO.getFormattedAddress())
        .setName(placeDTO.getName())
        .setTypes(placeTypeEntities)
        .setOpen(placeDTO.isOpen())
        .setIcon(placeDTO.getIcon())
        .setRating(placeDTO.getRating())
        .setUserRatingsTotal(placeDTO.getUserRatingsTotal());
  }
}
