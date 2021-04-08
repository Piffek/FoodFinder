package com.piwkosoft.foodFinder.Validators;

import com.piwkosoft.foodFinder.Validators.CustomAnnotations.AccountTypeEnumValueExist;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.AccountPlanEntity;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
public class AccountTypeEnumValidator implements ConstraintValidator<AccountTypeEnumValueExist, Object> {

  @Override
  public void initialize(final AccountTypeEnumValueExist constraintAnnotation) {
  }

  @Override
  public boolean isValid(final Object o, final ConstraintValidatorContext constraintValidatorContext) {
    final String value = (String) o;
    try {
      AccountPlanEntity.AccountPlan.lookup(value);
    } catch (RuntimeException e) {
      return false;
    }

    return true;
  }
}
