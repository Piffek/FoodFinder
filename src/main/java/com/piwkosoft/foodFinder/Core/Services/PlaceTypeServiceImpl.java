package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceTypeService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@Service
@Transactional
public class PlaceTypeServiceImpl implements PlaceTypeService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void createIfNotExist(final PlaceTypeEntity placeTypeEntity) {
    Query query = entityManager.createQuery("SELECT pte.id FROM PlaceTypeEntity pte WHERE pte.name = :name");
    query.setParameter("name", placeTypeEntity.getName());

    if(query.getResultList().size() == 0) {
      entityManager.persist(placeTypeEntity);
    }
  }

  @Override
  public PlaceTypeEntity findById(final Long id) {
    return entityManager.find(PlaceTypeEntity.class, id);
  }

  @Override
  public Set<PlaceTypeEntity> findTypesByName(final List<String> types) {
    return types.stream()
        .map(this::findTypeByName)
        .collect(Collectors.toSet());
  }

  @Override
  public PlaceTypeEntity findTypeByName(final String type) {
    Query query = entityManager.createQuery("FROM PlaceTypeEntity pte WHERE pte.name = :name");
    query.setParameter("name", type);
    return (PlaceTypeEntity) query.getSingleResult();
  }
}
