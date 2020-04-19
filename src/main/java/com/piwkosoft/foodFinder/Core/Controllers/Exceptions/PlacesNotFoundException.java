package com.piwkosoft.foodFinder.Core.Controllers.Exceptions;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 19.04.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public class PlacesNotFoundException extends RuntimeException {

  public PlacesNotFoundException(String message) {
    super(message);
  }
}