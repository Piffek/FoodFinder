package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.PlaceService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public PlaceEntity create(final PlaceEntity placeEntity) {
    entityManager.persist(placeEntity);
    return placeEntity;
  }

  @Override
  public PlaceEntity update(final PlaceEntity placeEntity) {
    entityManager.merge(placeEntity);
    return placeEntity;
  }

  @Override
  public PlaceEntity findById(final Long id) {
    return entityManager.find(PlaceEntity.class, id);
  }

  @Override
  public PlaceEntity findByNameAndAdress(final String name, final String adress) {
    final Query query = entityManager
        .createQuery("FROM PlaceEntity re WHERE re.name = :name AND re.formattedAdress = :formattedAdress");
    query.setParameter("name", name);
    query.setParameter("formattedAdress", adress);

    PlaceEntity result = null;

    try {
      result = (PlaceEntity) query.getSingleResult();
    } catch (final NoResultException e) {
      //TODO make logger
    }

    return result;
  }

  @Override
  public List<PlaceEntity> findAllPlaces() {
    final Query query = entityManager.createQuery("FROM PlaceEntity");
    return query.getResultList();
  }
}
