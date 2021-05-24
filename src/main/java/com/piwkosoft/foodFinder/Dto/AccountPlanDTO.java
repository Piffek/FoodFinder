package com.piwkosoft.foodFinder.Dto;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity.AccountPlan;
import java.util.Set;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project: FoodFinder
 *
 * Created on: 17.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Builder
@Accessors(chain = true)
public class AccountPlanDTO {

  private Long id;
  private AccountPlan accountPlan;
  private Set<Long> users;
}
