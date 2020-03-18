package com.example.foodFinder.Dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class RoleDTO {
  public String name;
  public Set<Long> users;
}
