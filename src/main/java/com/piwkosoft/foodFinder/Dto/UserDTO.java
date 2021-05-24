package com.piwkosoft.foodFinder.Dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

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
@Builder
@Accessors(chain = true)
public class UserDTO {

  private Long id;
  private String username;
  private String password;
  private String emailAdress;
  private String city;
  private Long accountPlan;
  private Set<Long> roles;
  private Date updatedDate;
  private Date createdDate;
  private Long activatedToken;
  private boolean enabled;
}