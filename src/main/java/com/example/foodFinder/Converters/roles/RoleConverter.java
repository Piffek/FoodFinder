package com.example.foodFinder.Converters.roles;

import com.example.foodFinder.Converters.Converter;
import com.example.foodFinder.Dto.RoleDTO;
import com.example.foodFinder.Persistance.Entities.RoleEntity;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class RoleConverter implements Converter<RoleDTO, RoleEntity> {

  @Override
  public RoleDTO convert(final RoleEntity roleEntity) {
    final RoleDTO roleDTO = new RoleDTO();
    roleDTO.setName(roleEntity.getName());
    roleDTO.setUsers(roleEntity.getUser().stream().map(UserEntity::getId).collect(Collectors.toSet()));

    return roleDTO;
  }
}
