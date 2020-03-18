package com.example.foodFinder.Facades.Interfaces;

import com.example.foodFinder.Dto.AccountPlanDTO;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;

public interface AccountFacade {
    AccountPlanDTO getUserAccountPlan(Long userId);
    AccountPlanDTO findAccountPlanByName(AccountPlan accountPlan);

}
