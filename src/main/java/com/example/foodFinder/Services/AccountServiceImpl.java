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
    public AcountType getUserAccountType(Long userId) {
        return null;
    }

    public enum AcountType {
        SOFT,
        STANDARD,
        PREMIUM
    }
}