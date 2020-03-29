package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.AccountPlanDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity.AccountPlan;

public interface AccountFacade {
    AccountPlanDTO getUserAccountPlan(Long userId);
    AccountPlanDTO findAccountPlanByName(AccountPlan accountPlan);

}
