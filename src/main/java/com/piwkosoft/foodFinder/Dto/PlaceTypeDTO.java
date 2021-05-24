package com.piwkosoft.foodFinder.Dto;

import java.util.Set;
import lombok.Builder;
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
@Builder
@Accessors(chain = true)
public class PlaceTypeDTO {

  public Long id;
  public String name;
  public Set<Long> places;
}
