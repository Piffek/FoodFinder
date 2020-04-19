package com.piwkosoft.foodFinder.Core.Controllers.Rest;

import com.piwkosoft.foodFinder.Core.Controllers.Exceptions.PlacesNotFoundException;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.PlaceFacade;
import com.piwkosoft.foodFinder.Dto.PlaceDTO;
import java.util.Collections;
import java.util.List;
import org.codehaus.groovy.runtime.ArrayUtil;
import org.hibernate.event.internal.DirtyCollectionSearchVisitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

  @GetMapping(value = "places", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<PlaceDTO> publicPlaces() {
    List<PlaceDTO> availablePlaces = placeFacade.findAllPlaces();
    if (CollectionUtils.isEmpty(availablePlaces)) {
      throw new PlacesNotFoundException("places not found");
    }
    return availablePlaces;
  }
}
