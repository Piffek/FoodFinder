package com.example.foodFinder.Dto;

import com.example.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class AccountPlanDTO {
  private Long id;
  private String accountPlan;
  private Set<Long> users;
}
