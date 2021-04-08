package com.piwkosoft.foodFinder.Dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project: FoodFinder
 *
 * Created on: 13.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@Accessors(chain = true)
public class VerificationTokenDTO {

  private Long id;
  private String token;
  private Date tokenExpiryDate; //current + 30m
  private Long user;
  private boolean used;
}
