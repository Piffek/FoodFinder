package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceTypeService;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft
 */
@Service
@Transactional
public class PlaceTypeServiceImpl implements PlaceTypeService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Long getCount(final String name) {
    final Query query = entityManager.createQuery("SELECT COUNT(pte.id) FROM PlaceTypeEntity pte WHERE pte.name = :name");
    query.setParameter("name", name);

    return (Long) query.getSingleResult();
  }

  @Override
  public void create(final PlaceTypeEntity placeTypeEntity) {
    entityManager.persist(placeTypeEntity);
  }


  @Override
  public PlaceTypeEntity findById(final Long id) {
    return entityManager.find(PlaceTypeEntity.class, id);
  }

  @Override
  public List<PlaceTypeEntity> findTypesByName(final List<String> types) {
    return types.stream()
        .map(this::findTypeByName)
        .collect(Collectors.toList());
  }

  @Override
  public PlaceTypeEntity findTypeByName(final String type) {
    final Query query = entityManager.createQuery("FROM PlaceTypeEntity pte WHERE pte.name = :name");
    query.setParameter("name", type);
    return (PlaceTypeEntity) query.getSingleResult();
  }
}
