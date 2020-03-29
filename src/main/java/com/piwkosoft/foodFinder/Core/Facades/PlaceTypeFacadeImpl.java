package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceTypeFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceTypeService;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import java.util.List;
import java.util.Set;
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
public class PlaceTypeFacadeImpl implements PlaceTypeFacade {

  private final PlaceTypeService placeTypeService;
  private final Converter<PlaceTypeDTO, PlaceTypeEntity> converter;
  private final ReverseConverter<PlaceTypeEntity, PlaceTypeDTO> reverseConverter;

  public PlaceTypeFacadeImpl(
      final PlaceTypeService placeTypeService,
      final Converter<PlaceTypeDTO, PlaceTypeEntity> converter,
      final ReverseConverter<PlaceTypeEntity, PlaceTypeDTO> reverseConverter) {
    this.placeTypeService = placeTypeService;
    this.converter = converter;
    this.reverseConverter = reverseConverter;
  }

  @Override
  public void createIfNotExist(final PlaceTypeDTO placeTypeDTO) {
    placeTypeService.createIfNotExist(reverseConverter.convert(placeTypeDTO, new PlaceTypeEntity()));
  }

  @Override
  public PlaceTypeDTO findById(final Long id) {
    return converter.convert(placeTypeService.findById(id));
  }

  @Override
  public Set<PlaceTypeDTO> findTypesByName(final List<String> types) {
    return converter.convertAll(placeTypeService.findTypesByName(types));
  }
}
