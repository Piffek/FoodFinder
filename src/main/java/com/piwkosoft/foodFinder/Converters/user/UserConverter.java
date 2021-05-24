package com.piwkosoft.foodFinder.Converters.user;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Dto.UserDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
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
    return UserDTO
        .builder()
        .id(userEntity.getId())
        .password(userEntity.getPassword())
        .emailAdress(userEntity.getEmailAdress())
        .username(userEntity.getUsername())
        .city(userEntity.getCity())
        .accountPlan(userEntity.getAccountPlan().getId())
        .activatedToken(userEntity.getActivatedToken().getId())
        .roles(userEntity.getRoles().stream().map(RoleEntity::getId).collect(Collectors.toSet()))
        .enabled(userEntity.isEnabled())
        .createdDate(userEntity.getCreatedDate())
        .updatedDate(userEntity.getUpdatedDate())
        .build();
  }
}
