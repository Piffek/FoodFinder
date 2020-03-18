package com.example.foodFinder.Services;

import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.AccountService;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class AccountServiceImpl implements AccountService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public AccountPlanEntity getUserAccountPlan(Long userId) {
        UserEntity userEntity = em.find(UserEntity.class, UserEntity.class);
        return userEntity.getAccountPlan();
    }

    @Override
    public AccountPlanEntity findAccountPlanByName(AccountPlan accountPlan) {
        Query query = em.createQuery("FROM AccountPlanEntity ape WHERE ape.accountPlan = :accountPlan");
        query.setParameter("accountPlan", accountPlan);
        return (AccountPlanEntity) query.getSingleResult();
    }

    @Override
    public AccountPlanEntity findById(Long id) {
        Query query = em.createQuery("FROM AccountPlanEntity ape WHERE ape.id = :id");
        query.setParameter("id", id);
        return (AccountPlanEntity) query.getSingleResult();
    }
}
