package com.piwkosoft.foodFinder.Converters.roles;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Dto.RoleDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
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
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class RoleConverter implements Converter<RoleDTO, RoleEntity> {

  @Override
  public RoleDTO convert(final RoleEntity roleEntity) {
    final RoleDTO roleDTO = new RoleDTO();
    roleDTO.setName(roleEntity.getName());
    roleDTO.setUsers(getUserIdsAssignedToRole(roleEntity));
    return roleDTO;
  }

  private Set<Long> getUserIdsAssignedToRole(final RoleEntity role) {
    return role.getUser()
        .stream()
        .map(UserEntity::getId)
        .collect(Collectors.toSet());
  }
}
