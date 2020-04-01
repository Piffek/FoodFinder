package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.CountryFacade;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.CountryEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.CountryService;
import com.piwkosoft.foodFinder.Dto.CountryDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 01.04.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class CountryFacadeImpl implements CountryFacade {

  private final Converter<CountryDTO, CountryEntity> converter;
  private final CountryService countryService;

  public CountryFacadeImpl(
      final Converter<CountryDTO, CountryEntity> converter,
      final CountryService countryService) {

    this.converter = converter;
    this.countryService = countryService;
  }

  @Override
  public List<CountryDTO> getAllCountries() {
    return converter.convertAll(countryService.getAllCountries());
  }
}
