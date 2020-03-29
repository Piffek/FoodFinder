package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.PlaceTypeEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RestaurantEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RestaurantService;
import java.util.List;
import java.util.Set;
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
  public void createOrUpdate(final RestaurantEntity restaurantEntity) {
    Query query = entityManager.createQuery("SELECT re.id FROM RestaurantEntity re WHERE re.name = :name AND re.formattedAdress = :formattedAdress");
    query.setParameter("name", restaurantEntity.getName());
    query.setParameter("formattedAdress", restaurantEntity.getFormattedAdress());

    if(query.getMaxResults() > 0) {
      update(restaurantEntity);
      return;
    }

    create(restaurantEntity);
  }

  @Override
  public void update(final RestaurantEntity restaurantEntity) {
    entityManager.merge(restaurantEntity);
  }

  @Override
  public void createRestaurants(final List<RestaurantEntity> restaurantEntities) {
    restaurantEntities
        .forEach(entityManager::persist);
  }

  @Override
  public RestaurantEntity findById(final Long id) {
    return entityManager.find(RestaurantEntity.class, id);
  }

  @Override
  public RestaurantEntity findByName(final String name, final boolean hasPlaceTypes) {
    Query query = entityManager.createQuery("FROM RestaurantEntity re WHERE re.name = :name");
    query.setParameter("name", name);

    return (RestaurantEntity) query.getSingleResult();
  }
}
