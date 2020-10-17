package com.piwkosoft.foodFinder.Resolvers;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 17.10.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@Component
public class PlaceUrlResolver extends AbstractUrlResolver<PlaceEntity> {

  private final static String PATTERN = "place";

  @Override
  public String resolve(final PlaceEntity object) {
    return PlaceUrlResolver.PATTERN + "/" +
        formatPartOfUrl(object.getName()) + "/" + object.getId();
  }
}
