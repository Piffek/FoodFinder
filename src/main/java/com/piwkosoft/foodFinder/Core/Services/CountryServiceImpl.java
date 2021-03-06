package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.CountryEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.CountryService;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@Service
public class CountryServiceImpl implements CountryService {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<CountryEntity> getAllCountries() {
    final Query query = entityManager.createQuery("FROM CountryEntity");
    return query.getResultList();
  }
}
