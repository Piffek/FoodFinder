package com.example.foodFinder.Dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 13.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) Piwko.
 */
@Getter
@Setter
@Accessors(chain = true)
public class VerificationTokenDTO {
  private Long id;
  private String token;
  private Date tokenExpiryDate; //current + 30m
}
