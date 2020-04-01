package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
public interface PlaceTypeService {
  void createIfNotExist(PlaceTypeEntity placeTypeEntity);
  PlaceTypeEntity findById(Long id);
  List<PlaceTypeEntity> findTypesByName(List<String> types);
  PlaceTypeEntity findTypeByName(String type);
}
