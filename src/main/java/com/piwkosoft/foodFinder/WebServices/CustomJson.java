package com.piwkosoft.foodFinder.WebServices;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 29.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface CustomJson <JSON_OBJECT> {
  JSON_OBJECT objectFromJson(final String url);
  String returnNextPageToken(final JSON_OBJECT restaurants);
  boolean hasNextPage(String token);
}
