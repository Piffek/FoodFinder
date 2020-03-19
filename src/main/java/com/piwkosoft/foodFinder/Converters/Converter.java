package com.piwkosoft.foodFinder.Converters;

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
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@FunctionalInterface
public interface Converter<OUT, IN> {
  OUT convert(IN in);
  default Set<OUT> convertAll(Set<IN> in) {
    return in.stream()
        .map(this::convert)
        .collect(Collectors.toSet());
  }
}
