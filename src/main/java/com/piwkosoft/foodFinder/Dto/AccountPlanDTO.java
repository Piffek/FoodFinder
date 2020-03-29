package com.piwkosoft.foodFinder.Dto;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity.AccountPlan;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@Accessors(chain = true)
public class AccountPlanDTO {
  private Long id;
  private AccountPlan accountPlan;
  private Set<Long> users;
}
