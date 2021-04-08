package com.piwkosoft.foodFinder.Converters.country;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.CountryEntity;
import com.piwkosoft.foodFinder.Dto.CountryDTO;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class CountryConverter implements Converter<CountryDTO, CountryEntity> {

  @Override
  public CountryDTO convert(final CountryEntity countryEntity) {
    return new CountryDTO().setName(countryEntity.getName());
  }
}
