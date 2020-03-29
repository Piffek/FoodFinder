package com.piwkosoft.foodFinder.Converters.accountPlan;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Dto.AccountPlanDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.UserService;
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
public class AccountPlanReverseConverter implements
    ReverseConverter<AccountPlanEntity, AccountPlanDTO> {

  private final UserService userService;

  public AccountPlanReverseConverter(
      final UserService userService) {
    this.userService = userService;
  }

  @Override
  public AccountPlanEntity convert(final AccountPlanDTO accountPlanDTO,
      final AccountPlanEntity accountPlanEntity) {
    Set<UserEntity> userEntities = accountPlanDTO.getUsers().stream()
        .map(userService::findById)
        .collect(Collectors.toSet());

    accountPlanEntity.setId(accountPlanDTO.getId());
    accountPlanEntity.setAccountPlan(accountPlanDTO.getAccountPlan());
    accountPlanEntity.setUsers(userEntities);
    return accountPlanEntity;
  }
}
