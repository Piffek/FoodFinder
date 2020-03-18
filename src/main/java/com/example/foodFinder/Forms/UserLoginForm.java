package com.example.foodFinder.Forms;

import javax.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 16.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */

@Getter
@Setter
public class UserLoginForm {
  @Email
  private String email;
  private String password;
}
