package com.piwkosoft.foodFinder.Converters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project: FoodFinder
 *
 * Created on: 17.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@FunctionalInterface
public interface Converter<OUT, IN> {
  OUT convert(IN in);
  default List<OUT> convertAll(List<IN> in) {
    return in.stream()
        .map(this::convert)
        .collect(Collectors.toList());
  }
}
