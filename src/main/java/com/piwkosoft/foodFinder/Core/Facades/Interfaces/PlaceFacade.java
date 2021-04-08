package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import java.util.List;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface PlaceFacade {

  void createOrUpdate(PlaceDTO placeDTOS);

  List<PlaceDTO> findAllPlaces();
}
