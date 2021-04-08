package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.UserDTO;

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface UserFacade {

  Long createUser(UserDTO userDto);

  void setAsEnable(UserDTO userDto);

  UserDTO findByUsername(String username);

  UserDTO findById(Long id);
}
