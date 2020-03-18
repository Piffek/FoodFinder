package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;

public interface AccountService {
    AccountPlanEntity getUserAccountPlan(Long userId);
    AccountPlanEntity findAccountPlanByName(String accountPlan);
    AccountPlanEntity findById(Long id);

}
