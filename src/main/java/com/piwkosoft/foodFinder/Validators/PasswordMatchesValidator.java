package com.piwkosoft.foodFinder.Validators;

import com.piwkosoft.foodFinder.Forms.UserRegistrationForm;
import com.piwkosoft.foodFinder.Validators.CustomAnnotations.PasswordMatches;

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
public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

  @Override
  public void initialize(final PasswordMatches passwordMatches) {

  }

  @Override
  public boolean isValid(final Object o, final ConstraintValidatorContext constraintValidatorContext) {
    final UserRegistrationForm userRegistrationForm = (UserRegistrationForm) o;

    if (userRegistrationForm != null && userRegistrationForm.getPassword() != null) {
      return userRegistrationForm.getPassword().equals(userRegistrationForm.getMatchingPassword());
    }

    return false;
  }
}
