package com.piwkosoft.foodFinder.Converters.roles;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Dto.RoleDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 17.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@Component
public class RoleReverseConverter implements ReverseConverter<RoleEntity, RoleDTO> {

  private final UserService userService;

  public RoleReverseConverter(final UserService userService) {
    this.userService = userService;
  }

  @Override
  public RoleEntity convert(final RoleDTO roleDTO, final RoleEntity roleEntity) {
    roleEntity.setName(roleDTO.getName());
    roleEntity.setUser(getUsersByIds(roleDTO.getUsers()));
    return roleEntity;
  }

  private Set<UserEntity> getUsersByIds(final Set<Long> ids) {
    return ids.stream()
        .map(userService::findById)
        .collect(Collectors.toSet());
  }
}
