package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.UserService;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Project: FoodFinder
 *
 * Created on: 16.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Long createUser(final UserEntity userEntity) {
    em.persist(userEntity);
    return userEntity.getId();
  }

  @Override
  public void updateUser(final UserEntity userEntity) {
    em.merge(userEntity);
  }

  @Override
  public UserEntity findByUsername(final String username) {
    final Query query = em.createQuery("FROM UserEntity ue WHERE ue.username = :username");
    query.setParameter("username", username);
    UserEntity userEntity = null;
    try {
      userEntity = (UserEntity) query.getSingleResult();
    } catch (final NoResultException e) {
      //TODO exception
    }

    return userEntity;
  }

  @Override
  public UserEntity findById(final Long id) {
    final Query query = em.createQuery("FROM UserEntity ue WHERE ue.id = :id");
    query.setParameter("id", id);
    return (UserEntity) query.getSingleResult();
  }

  @Override
  public List<RoleEntity> getRolesByUsername(final String username) {
    final Query query = em.createQuery("SELECT ue.roles FROM UserEntity ue WHERE ue.username = :username");
    query.setParameter("username", username);
    return (List<RoleEntity>) query.getResultList();
  }
}