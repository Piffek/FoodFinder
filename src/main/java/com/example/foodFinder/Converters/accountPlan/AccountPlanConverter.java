package com.example.foodFinder.Converters.accountPlan;

import com.example.foodFinder.Converters.Converter;
import com.example.foodFinder.Dto.AccountPlanDTO;
import com.example.foodFinder.Dto.UserDTO;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
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
public class AccountPlanConverter implements Converter<AccountPlanDTO, AccountPlanEntity> {

  @Override
  public AccountPlanDTO convert(AccountPlanEntity accountPlanEntity) {
    return new AccountPlanDTO()
        .setId(accountPlanEntity.getId())
        .setAccountPlan(AccountPlanEntity.AccountPlan.lookup(accountPlanEntity.getAccountPlan()))
        .setUsers(accountPlanEntity.getUsers().stream().map(UserEntity::getId).collect(Collectors.toSet()));
  }
}
