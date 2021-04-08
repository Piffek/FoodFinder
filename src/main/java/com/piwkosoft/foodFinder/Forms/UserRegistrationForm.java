package com.piwkosoft.foodFinder.Forms;

import com.piwkosoft.foodFinder.Validators.CustomAnnotations.AccountTypeEnumValueExist;
import com.piwkosoft.foodFinder.Validators.CustomAnnotations.PasswordMatches;
import com.piwkosoft.foodFinder.Validators.CustomAnnotations.PasswordSize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * Project: FoodFinder
 *
 * Created on: 16.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@PasswordMatches(message = "{validation.constraints.password.dont.match.message}")
public class UserRegistrationForm {

  @Email
  @NotNull(message = "{validation.constraints.email.not.null}")
  private String emailAdress;

  @PasswordSize(4)
  private String password;

  private String matchingPassword;

  @NotNull(message = "{validation.constraints.city.not.null}")
  private String city;

  @NotNull(message = "{validation.constraints.accountPlan.not.null}")
  @AccountTypeEnumValueExist
  private String accountPlan;
}
