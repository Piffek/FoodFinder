package com.piwkosoft.foodFinder.Resolvers;


import org.apache.commons.lang3.StringUtils;

/**
 * Project: FoodFinder
 *
 * Created on: 17.10.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
public abstract class AbstractUrlResolver<T> {

  abstract String resolve(final T object);

  protected String formatPartOfUrl(final String url) {
    final String replacedUrl = url
        .replaceAll("\"", "")
        .replaceAll(" ", "-")
        .replaceAll("/", "");
    return StringUtils.stripAccents(replacedUrl);
  }

}
