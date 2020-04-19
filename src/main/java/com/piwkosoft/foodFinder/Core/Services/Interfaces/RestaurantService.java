package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface RestaurantService {
  PlaceEntity create(PlaceEntity placeEntity);
  void update(PlaceEntity placeEntity);
  PlaceEntity findById(Long id);
  PlaceEntity findByNameAndAdress(String name, String adress);
}
