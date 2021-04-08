package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
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
public interface PlaceService {

  PlaceEntity create(PlaceEntity placeEntity);

  PlaceEntity update(PlaceEntity placeEntity);

  PlaceEntity findById(Long id);

  PlaceEntity findByNameAndAdress(String name, String adress);

  List<PlaceEntity> findAllPlaces();
}
