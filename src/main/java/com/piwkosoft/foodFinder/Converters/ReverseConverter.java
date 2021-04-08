package com.piwkosoft.foodFinder.Converters;

/**
 * Project: FoodFinder
 *
 * Created on: 17.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@FunctionalInterface
public interface ReverseConverter<OUT, IN> {
  OUT convert(IN in, OUT out);
}
