package com.example.foodFinder.Persistance.Services.Interfaces;

import com.example.foodFinder.Persistance.Services.AccountServiceImpl;

public interface AccountService {
    AccountServiceImpl.AcountType getUserAccountType(Long userId);

}
