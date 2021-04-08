package com.piwkosoft.foodFinder.Core.Persistance.Entities;

import com.piwkosoft.foodFinder.Utils.LookupUtil;
import java.io.Serializable;
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
import lombok.Getter;
import lombok.Setter;

/**
 * Project: FoodFinder
 *
 * Created on: 17.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@Entity
@Table(name = "account_plan")
public class AccountPlanEntity implements Serializable {

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
