package com.piwkosoft.foodFinder.Forms;

import com.piwkosoft.foodFinder.Validators.CustomAnnotations.PasswordSize;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 19.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
public class UserLoginForm {

  @Size(message = "{validation.constraints.name.size.message}", min = 4, max = 30)
  private String name;

  @PasswordSize(4)
  private String password;
}
