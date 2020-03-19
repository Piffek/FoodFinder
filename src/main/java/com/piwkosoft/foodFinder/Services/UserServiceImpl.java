package com.piwkosoft.foodFinder.Services;

import com.piwkosoft.foodFinder.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Services.Interfaces.UserService;
import java.util.List;
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
  public UserEntity findById(final Long id) {
    Query query = em.createQuery("FROM UserEntity ue WHERE ue.id = :id");
    query.setParameter("id", id);
    return (UserEntity) query.getSingleResult();
  }

  @Override
  public List<RoleEntity> getRolesByUsername(final String username) {
    Query query = em.createQuery("SELECT ue.roles FROM UserEntity ue WHERE ue.username = :username");
    query.setParameter("username", username);
    return (List<RoleEntity>) query.getResultList();
  }
}