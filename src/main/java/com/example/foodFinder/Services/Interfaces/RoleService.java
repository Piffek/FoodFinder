package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Persistance.Entities.RoleEntity;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface RoleService {
  RoleEntity findById(Long id);
}
