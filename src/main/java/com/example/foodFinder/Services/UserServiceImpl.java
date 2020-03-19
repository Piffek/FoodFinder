package com.example.foodFinder.Services;

import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.UserService;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

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
    Query query = em.createQuery("FROM UserEntity ue WHERE ue.username = :username");
    query.setParameter("username", username);
    UserEntity userEntity;
    try {
      userEntity = (UserEntity) query.getSingleResult();
    } catch (NoResultException e) {
      return null;
    }

    return userEntity;
  }

  @Override
  public UserEntity findById(Long id) {
    Query query = em.createQuery("FROM UserEntity ue WHERE ue.id = :id");
    query.setParameter("id", id);
    return (UserEntity) query.getSingleResult();
  }
}