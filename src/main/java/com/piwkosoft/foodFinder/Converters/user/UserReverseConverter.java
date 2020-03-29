package com.piwkosoft.foodFinder.Converters.user;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Dto.UserDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.VerificationTokenEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.AccountService;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RoleService;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.TokenService;
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
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class UserReverseConverter implements ReverseConverter<UserEntity, UserDTO> {

  private final AccountService accountService;
  private final TokenService tokenService;
  private final RoleService roleService;

  public UserReverseConverter(
      final AccountService accountService,
      final TokenService tokenService,
      final RoleService roleService) {
    this.accountService = accountService;
    this.tokenService = tokenService;
    this.roleService = roleService;
  }

  @Override
  public UserEntity convert(UserDTO userDTO, UserEntity userEntity) {
    final AccountPlanEntity accountPlanEntity = accountService.findById(userDTO.getAccountPlan());
    final VerificationTokenEntity verificationTokenEntity = tokenService.findById(userDTO.getActivatedToken());
    final Set<RoleEntity> roleEntities = userDTO.getRoles().stream().map(roleService::findById).collect(
        Collectors.toSet());

    return userEntity
        .setId(userDTO.getId())
        .setPassword(userDTO.getPassword())
        .setEmailAdress(userDTO.getEmailAdress())
        .setUsername(userDTO.getUsername())
        .setCity(userDTO.getCity())
        .setAccountPlan(accountPlanEntity)
        .setActivatedToken(verificationTokenEntity)
        .setRoles(roleEntities)
        .setEnabled(userDTO.isEnabled())
        .setCreatedDate(userDTO.getCreatedDate())
        .setUpdatedDate(userDTO.getUpdatedDate());
  }
}