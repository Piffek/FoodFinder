package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
import com.piwkosoft.foodFinder.Dto.RestaurantDTO;
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
public interface RestaurantFacade {
  void createOrUpdate(List<RestaurantDTO> restaurantDTOs);
}
