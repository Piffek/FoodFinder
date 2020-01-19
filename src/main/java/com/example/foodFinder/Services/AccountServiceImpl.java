package com.example.foodFinder.Services;

import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.AccountService;
import com.example.foodFinder.Utils.LookupUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public AccountPlan getUserAccountPlan(Long userId) {
        UserEntity userEntity = entityManager.find(UserEntity.class, UserEntity.class);
        return userEntity.getAccountPlan();
    }

    public enum AccountPlan {
        soft,
        standard,
        premium;

        static public AccountPlan lookup(String id) {
            return LookupUtil.lookup(AccountPlan.class, id);
        }
    }
}
