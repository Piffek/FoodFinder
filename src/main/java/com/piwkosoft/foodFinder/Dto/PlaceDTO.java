package com.piwkosoft.foodFinder.Dto;

import java.math.BigDecimal;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@Accessors(chain = true)
public class PlaceDTO {

  private String name;
  private Set<Long> types;
  private String formattedAddress;
  private String icon;
  private boolean isOpen;
  private Double rating;
  private BigDecimal userRatingsTotal;

}
