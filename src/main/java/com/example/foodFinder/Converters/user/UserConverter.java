package com.example.foodFinder.Converters.user;

import com.example.foodFinder.Converters.Converter;
import com.example.foodFinder.Dto.AccountPlanDTO;
import com.example.foodFinder.Dto.UserDTO;
import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.example.foodFinder.Persistance.Entities.RoleEntity;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Persistance.Entities.VerificationTokenEntity;
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
