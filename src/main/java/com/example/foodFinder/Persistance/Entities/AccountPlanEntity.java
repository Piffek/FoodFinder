package com.example.foodFinder.Persistance.Entities;

import com.example.foodFinder.Services.AccountServiceImpl;
import com.example.foodFinder.Utils.LookupUtil;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@Entity
@Table(name = "account_plan")
public class AccountPlanEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private AccountPlan accountPlan;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountPlan")
  private Set<UserEntity> users;



  public enum AccountPlan {
    soft,
    standard,
    premium;

    static public AccountPlan lookup(String id) {
      return LookupUtil.lookup(AccountPlan.class, id);
    }
  }
}
