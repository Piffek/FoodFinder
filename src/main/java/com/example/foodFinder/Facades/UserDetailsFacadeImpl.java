package com.example.foodFinder.Facades;

import com.example.foodFinder.Facades.Interfaces.UserDetailsFacade;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 19.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class UserDetailsFacadeImpl implements UserDetailsFacade {

  private final UserDetailsService userDetailsService;

  public UserDetailsFacadeImpl(
      final UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    return userDetailsService.loadUserByUsername(username);
  }
}
