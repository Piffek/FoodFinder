package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import java.util.List;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface PlaceService {
  PlaceEntity create(PlaceEntity placeEntity);
  void update(PlaceEntity placeEntity);
  PlaceEntity findById(Long id);
  PlaceEntity findByNameAndAdress(String name, String adress);
  List<PlaceEntity> findAllPlaces();
}
