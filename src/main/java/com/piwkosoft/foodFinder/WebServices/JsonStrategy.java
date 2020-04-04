package com.piwkosoft.foodFinder.WebServices;

import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 04.04.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class JsonStrategy {
  private CustomJson customJson;

  public void setJsonStrategy(final CustomJson customJson) {
    this.customJson = customJson;
  }

  public CustomJson json() {
    return customJson;
  }
}