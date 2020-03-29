package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity.AccountPlan;

public interface AccountService {
    AccountPlanEntity getUserAccountPlan(Long userId);
    AccountPlanEntity findAccountPlanByName(AccountPlan accountPlan);
    AccountPlanEntity findById(Long id);

}
