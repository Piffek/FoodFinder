package com.piwkosoft.foodFinder.Core.Services;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.VerificationTokenEntity;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.TokenService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 13.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Service
@Transactional
public class TokenServiceImpl implements TokenService {
  private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  public VerificationTokenEntity generateSecretToken() {
    final VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity();
    em.persist(verificationTokenEntity);

    return verificationTokenEntity;
  }

  @Override
  public VerificationTokenEntity findByToken(final String token) {
    Query query = em.createQuery("FROM VerificationTokenEntity vte WHERE vte.token = :token");
    query.setParameter("token", token);
    VerificationTokenEntity verificationTokenEntity;
    try {
      verificationTokenEntity = (VerificationTokenEntity) query.getSingleResult();
    } catch (final NoResultException e) {
      return null;
    }

    return verificationTokenEntity;
  }

  @Override
  public VerificationTokenEntity findById(final Long id) {
    return em.find(VerificationTokenEntity.class, id);
  }

  @Override
  public void update(final VerificationTokenEntity verificationTokenEntity) {
    em.merge(verificationTokenEntity);
  }
}
