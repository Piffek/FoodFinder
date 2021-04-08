package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.AccountPlanDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity.AccountPlan;

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface AccountFacade {

  AccountPlanDTO getUserAccountPlan(Long userId);

  AccountPlanDTO findAccountPlanByName(AccountPlan accountPlan);
}
