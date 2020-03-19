package com.example.foodFinder.Facades.Interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 19.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface UserDetailsFacade {
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
