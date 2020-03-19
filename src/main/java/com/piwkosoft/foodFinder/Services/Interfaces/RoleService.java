package com.piwkosoft.foodFinder.Services.Interfaces;

import com.piwkosoft.foodFinder.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Persistance.Entities.RoleEntity.Role;

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
