package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceService;
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
  private final Converter<PlaceDTO, PlaceEntity> converter;

  private final PlaceService placeService;

  public PlaceFacadeImpl(
      final ReverseConverter<PlaceEntity, PlaceDTO> reverseConverter,
      final Converter<PlaceDTO, PlaceEntity> converter,
      final PlaceService placeService) {
    this.reverseConverter = reverseConverter;
    this.converter = converter;
    this.placeService = placeService;
  }

  @Override
  public void createOrUpdate(final List<PlaceDTO> placeDTOS) {
    placeDTOS.stream()
        .map(restaurant -> placeService
            .findByNameAndAdress(restaurant.getName(), restaurant.getFormattedAddress()))
        .filter(Objects::nonNull)
        .forEach(placeService::update);

    placeDTOS.stream()
        .filter(restaurant -> placeService
            .findByNameAndAdress(restaurant.getName(), restaurant.getFormattedAddress()) == null)
        .map(restaurant -> reverseConverter.convert(restaurant, new PlaceEntity()))
        .forEach(placeService::create);
  }

  @Override
  public List<PlaceDTO> findAllPlaces() {
    return converter.convertAll(placeService.findAllPlaces());
  }
}
