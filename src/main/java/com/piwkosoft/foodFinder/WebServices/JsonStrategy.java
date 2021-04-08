package com.piwkosoft.foodFinder.WebServices;

import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 04.04.2020
 *
 * Author    : Patryk Piwko
 *
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