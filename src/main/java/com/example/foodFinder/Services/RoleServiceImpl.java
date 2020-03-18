package com.example.foodFinder.Services;

import com.example.foodFinder.Persistance.Entities.RoleEntity;
import com.example.foodFinder.Services.Interfaces.RoleService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
public class RoleServiceImpl implements RoleService {

  @PersistenceContext
  private EntityManager em;

  @Override
  public RoleEntity findById(final Long id) {
    return em.find(RoleEntity.class, id);
  }
}
