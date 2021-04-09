package com.piwkosoft.foodFinder.WebServices;

/**
 * Project: FoodFinder
 *
 * Created on: 29.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface CustomJson<JSON_OBJECT> {

  JSON_OBJECT objectFromJson(String url);
}
