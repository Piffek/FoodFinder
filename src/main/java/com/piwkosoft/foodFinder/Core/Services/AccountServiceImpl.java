package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.AccountService;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
public class AccountServiceImpl implements AccountService {

  @PersistenceContext
  private EntityManager em;

  @Override
  public AccountPlanEntity getUserAccountPlan(Long userId) {
    final UserEntity userEntity = em.find(UserEntity.class, UserEntity.class);
    return userEntity.getAccountPlan();
  }

  @Override
  public AccountPlanEntity findAccountPlanByName(AccountPlan accountPlan) {
    final Query query = em.createQuery("FROM AccountPlanEntity ape WHERE ape.accountPlan = :accountPlan");
    query.setParameter("accountPlan", accountPlan);
    return (AccountPlanEntity) query.getSingleResult();
  }

  @Override
  public AccountPlanEntity findById(Long id) {
    final Query query = em.createQuery("FROM AccountPlanEntity ape WHERE ape.id = :id");
    query.setParameter("id", id);
    return (AccountPlanEntity) query.getSingleResult();
  }
}
