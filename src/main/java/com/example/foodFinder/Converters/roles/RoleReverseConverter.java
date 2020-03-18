package com.example.foodFinder.Converters.roles;

import com.example.foodFinder.Converters.ReverseConverter;
import com.example.foodFinder.Dto.RoleDTO;
import com.example.foodFinder.Persistance.Entities.RoleEntity;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.UserService;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
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
    Set<UserEntity> userEntities = roleDTO.getUsers().stream().map(userService::findById).collect(
        Collectors.toSet());
    roleEntity.setName(roleDTO.getName());
    roleEntity.setUser(userEntities);
    return roleEntity;
  }
}
