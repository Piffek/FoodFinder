package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.CountryDTO;
import java.util.List;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 01.04.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface CountryFacade {
  List<CountryDTO> getAllCountries();
}
