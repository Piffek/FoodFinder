package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RestaurantService;
import java.util.List;
import java.util.Set;
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
  public RestaurantEntity create(final RestaurantEntity restaurantEntity) {
    entityManager.persist(restaurantEntity);
    return restaurantEntity;
  }

  @Override
  public void update(final RestaurantEntity restaurantEntity) {
    entityManager.merge(restaurantEntity);
  }

  @Override
  public RestaurantEntity findById(final Long id) {
    return entityManager.find(RestaurantEntity.class, id);
  }

  @Override
  public RestaurantEntity findByNameAndAdress(final String name, final String adress) {
    Query query = entityManager.createQuery("FROM RestaurantEntity re WHERE re.name = :name AND re.formattedAdress = :formattedAdress");
    query.setParameter("name", name);
    query.setParameter("formattedAdress", adress);

    RestaurantEntity result;

    try {
      result = (RestaurantEntity) query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }

    return result;
  }
}
