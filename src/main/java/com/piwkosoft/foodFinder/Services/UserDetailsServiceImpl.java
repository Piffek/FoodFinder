package com.piwkosoft.foodFinder.Services;

import com.piwkosoft.foodFinder.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Services.Interfaces.UserService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 16.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

  private final UserService userService;

  public UserDetailsServiceImpl(final UserService userService) {
    this.userService = userService;
  }


  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException, DisabledException {
    final UserEntity userEntity = userService.findByUsername(username);
    if (userEntity == null) {
      logger.debug("username {} not found", username);
      throw new UsernameNotFoundException(username);
    }

    final List<RoleEntity> roles = userService.getRolesByUsername(username);

    List<GrantedAuthority> authorities = roles.stream()
        .map(RoleEntity::getName)
        .map(Objects::toString)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(),
        true, true, true,
        authorities);
  }
}
