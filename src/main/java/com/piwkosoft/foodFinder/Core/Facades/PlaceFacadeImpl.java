package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
  public void createOrUpdate(final PlaceDTO placeDTO) {
    final PlaceEntity place = placeService.findByNameAndAdress(placeDTO.getName(), placeDTO.getFormattedAddress());

    if(place == null) {
      final PlaceEntity placeToCreate = reverseConverter.convert(placeDTO, new PlaceEntity());
      placeService.create(placeToCreate);
    }

    placeService.update(place);
  }

  @Override
  public List<PlaceDTO> findAllPlaces() {
    return converter.convertAll(placeService.findAllPlaces());
  }
}
