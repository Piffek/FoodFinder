package com.piwkosoft.foodFinder.Converters.accountPlan;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Dto.AccountPlanDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import java.util.Objects;
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
public class AccountPlanConverter implements Converter<AccountPlanDTO, AccountPlanEntity> {

  @Override
  public AccountPlanDTO convert(AccountPlanEntity accountPlanEntity) {
    return new AccountPlanDTO()
        .setId(accountPlanEntity.getId())
        .setAccountPlan(accountPlanEntity.getAccountPlan())
        .setUsers(accountPlanEntity.getUsers().stream()
            .filter(Objects::nonNull)
            .map(UserEntity::getId)
            .collect(Collectors.toSet()));
  }
}
