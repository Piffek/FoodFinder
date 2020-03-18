package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Persistance.Entities.RoleEntity;
import com.example.foodFinder.Persistance.Entities.RoleEntity.Role;

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
  RoleEntity findIdByRole(Role roleName);
}
