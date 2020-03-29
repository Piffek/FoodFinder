package com.piwkosoft.foodFinder.Dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.internal.constraintvalidators.bv.time.past.PastValidatorForYear;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@Accessors(chain = true)
public class PlaceTypeDTO {
  public Long id;
  public String name;
  public Set<Long> restaurants;
}
