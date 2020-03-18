package com.example.foodFinder.Converters;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@FunctionalInterface
public interface ReverseConverter<OUT, IN> {
  OUT convert(IN in, OUT out);
}
