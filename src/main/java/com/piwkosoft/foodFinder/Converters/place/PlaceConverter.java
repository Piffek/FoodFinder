package com.piwkosoft.foodFinder.Converters.place;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import com.piwkosoft.foodFinder.Resolvers.PlaceUrlResolver;
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
public class PlaceConverter implements Converter<PlaceDTO, PlaceEntity> {

  private final PlaceUrlResolver placeUrlResolver;

  public PlaceConverter(final PlaceUrlResolver placeUrlResolver) {
    this.placeUrlResolver = placeUrlResolver;
  }

  @Override
  public PlaceDTO convert(final PlaceEntity placeEntity) {
    final Set<Long> typesId = placeEntity.getTypes().stream()
        .filter(Objects::nonNull)
        .map(PlaceTypeEntity::getId)
        .collect(Collectors.toSet());

    return new PlaceDTO()
        .setFormattedAddress(placeEntity.getFormattedAdress())
        .setName(placeEntity.getName())
        .setTypes(typesId)
        .setOpen(placeEntity.isOpen())
        .setIcon(placeEntity.getIcon())
        .setRating(placeEntity.getRating())
        .setUserRatingsTotal(placeEntity.getUserRatingsTotal())
        .setUrl(placeUrlResolver.resolve(placeEntity));
  }
}
