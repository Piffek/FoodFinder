package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.AccountServiceImpl;

public interface AccountService {
    AccountServiceImpl.AccountPlan getUserAccountPlan(Long userId);
}
