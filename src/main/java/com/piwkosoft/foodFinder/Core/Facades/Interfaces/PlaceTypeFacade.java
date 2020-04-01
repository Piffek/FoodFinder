package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Dto.PlaceTypeDTO;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public interface PlaceTypeFacade {
  void createIfNotExist(PlaceTypeDTO placeTypeDTO);
  PlaceTypeDTO findById(Long id);
  List<PlaceTypeDTO> findTypesByName(List<String> types);
}
