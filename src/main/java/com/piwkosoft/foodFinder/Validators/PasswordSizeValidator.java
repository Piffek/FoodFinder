package com.piwkosoft.foodFinder.Validators;

import com.piwkosoft.foodFinder.Validators.CustomAnnotations.PasswordSize;

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
public class PasswordSizeValidator implements ConstraintValidator<PasswordSize, Object> {

  private int defaultMinPasswordSize;

  @Override
  public void initialize(final PasswordSize constraintAnnotation) {
    this.defaultMinPasswordSize = constraintAnnotation.value();
  }

  @Override
  public boolean isValid(final Object o, final ConstraintValidatorContext constraintValidatorContext) {
    final String string = (String) o;

    if (string != null) {
      return string.length() > defaultMinPasswordSize;
    }

    return false;
  }
}
