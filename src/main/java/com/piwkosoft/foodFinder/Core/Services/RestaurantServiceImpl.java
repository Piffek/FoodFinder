package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RestaurantService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public PlaceEntity create(final PlaceEntity placeEntity) {
    entityManager.persist(placeEntity);
    return placeEntity;
  }

  @Override
  public void update(final PlaceEntity placeEntity) {
    entityManager.merge(placeEntity);
  }

  @Override
  public PlaceEntity findById(final Long id) {
    return entityManager.find(PlaceEntity.class, id);
  }

  @Override
  public PlaceEntity findByNameAndAdress(final String name, final String adress) {
    Query query = entityManager.createQuery("FROM RestaurantEntity re WHERE re.name = :name AND re.formattedAdress = :formattedAdress");
    query.setParameter("name", name);
    query.setParameter("formattedAdress", adress);

    PlaceEntity result;

    try {
      result = (PlaceEntity) query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }

    return result;
  }
}
