package com.example.foodFinder.Services;

import com.example.foodFinder.Services.Interfaces.AccountService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AccountPlan getUserAccountPlan(Long userId) {
        return null;
    }

    public enum AccountPlan {
        SOFT,
        STANDARD,
        PREMIUM
    }
}
