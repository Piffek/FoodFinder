package com.piwkosoft.foodFinder.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.AccountPlanDTO;
import com.piwkosoft.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;

public interface AccountFacade {
    AccountPlanDTO getUserAccountPlan(Long userId);
    AccountPlanDTO findAccountPlanByName(AccountPlan accountPlan);

}
