package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity.Role;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RoleService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 *
 * Created on: 17.03.2020
 *
 * Author    : Patryk Piwko
 *
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

  @Override
  public RoleEntity findIdByRole(final Role roleName) {
    final Query query = em.createQuery("FROM RoleEntity re WHERE re.name = :name");
    query.setParameter("name", roleName);
    RoleEntity role;
    try {
      role = (RoleEntity) query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
    return role;
  }

}
