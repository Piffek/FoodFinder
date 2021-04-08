package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public interface PlaceTypeFacade {

  void createIfNotExist(PlaceTypeDTO placeTypeDTO);

  PlaceTypeDTO findById(Long id);

  List<PlaceTypeDTO> findTypesByName(List<String> types);
}
