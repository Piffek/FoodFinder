package com.piwkosoft.foodFinder.Services.Interfaces;

import com.piwkosoft.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;

public interface AccountService {
    AccountPlanEntity getUserAccountPlan(Long userId);
    AccountPlanEntity findAccountPlanByName(AccountPlan accountPlan);
    AccountPlanEntity findById(Long id);

}
