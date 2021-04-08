package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.CountryDTO;
import java.util.List;

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface CountryFacade {

  List<CountryDTO> getAllCountries();
}
