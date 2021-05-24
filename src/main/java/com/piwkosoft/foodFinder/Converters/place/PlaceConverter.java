package com.piwkosoft.foodFinder.Converters.place;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
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
public class PlaceConverter implements Converter<PlaceDTO, PlaceEntity> {

  @Override
  public PlaceDTO convert(final PlaceEntity placeEntity) {
    final Set<Long> typesId = placeEntity.getTypes().stream()
        .filter(Objects::nonNull)
        .map(PlaceTypeEntity::getId)
        .collect(Collectors.toSet());

    return PlaceDTO.builder()
        .formattedAddress(placeEntity.getFormattedAdress())
        .name(placeEntity.getName())
        .types(typesId)
        .isOpen(placeEntity.isOpen())
        .icon(placeEntity.getIcon())
        .rating(placeEntity.getRating())
        .userRatingsTotal(placeEntity.getUserRatingsTotal())
        .build();
  }
}
