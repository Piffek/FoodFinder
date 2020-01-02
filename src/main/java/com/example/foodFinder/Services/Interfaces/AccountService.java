package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Services.AccountServiceImpl;

public interface AccountService {
    AccountServiceImpl.AcountType getUserAccountType(Long userId);

}
