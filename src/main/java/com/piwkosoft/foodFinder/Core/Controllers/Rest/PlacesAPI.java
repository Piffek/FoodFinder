package com.piwkosoft.foodFinder.Core.Controllers.Rest;

import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 19.04.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft
 */
@RestController
@RequestMapping("api")
public class PlacesAPI {
  private final PlaceFacade placeFacade;

  public PlacesAPI(final PlaceFacade placeFacade) {
    this.placeFacade = placeFacade;
  }
}
