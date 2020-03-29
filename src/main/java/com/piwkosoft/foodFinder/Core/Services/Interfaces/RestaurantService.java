package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
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
public interface RestaurantService {
  RestaurantEntity create(RestaurantEntity restaurantEntity);
  void update(RestaurantEntity restaurantEntity);
  RestaurantEntity findById(Long id);
  RestaurantEntity findByNameAndAdress(String name, String adress);
}
