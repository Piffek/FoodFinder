package com.piwkosoft.foodFinder.Converters.user;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Dto.UserDTO;
import com.piwkosoft.foodFinder.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Persistance.Entities.UserEntity;
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
public class UserConverter implements Converter<UserDTO, UserEntity> {

  @Override
  public UserDTO convert(final UserEntity userEntity) {
    return new UserDTO()
        .setId(userEntity.getId())
        .setPassword(userEntity.getPassword())
        .setEmailAdress(userEntity.getEmailAdress())
        .setUsername(userEntity.getUsername())
        .setCity(userEntity.getCity())
        .setAccountPlan(userEntity.getAccountPlan().getId())
        .setActivatedToken(userEntity.getActivatedToken().getId())
        .setRoles(userEntity.getRoles().stream().map(RoleEntity::getId).collect(Collectors.toSet()))
        .setEnabled(userEntity.isEnabled())
        .setCreatedDate(userEntity.getCreatedDate())
        .setUpdatedDate(userEntity.getUpdatedDate());
  }
}
