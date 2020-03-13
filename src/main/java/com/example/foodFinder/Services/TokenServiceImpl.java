package com.example.foodFinder.Services;

import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Mapper.VerificationTokenMapper;
import com.example.foodFinder.Persistance.Entities.VerificationTokenEntity;
import com.example.foodFinder.Services.Interfaces.TokenService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class TokenServiceImpl implements TokenService {
  private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

  @PersistenceContext
  private EntityManager em;

  @Override
  @Transactional
  public VerificationTokenDTO generateSecretToken() {
    final VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity();
    em.persist(verificationTokenEntity);

    logger.debug("create token {}", verificationTokenEntity.getToken());
    return VerificationTokenMapper.toDTO(verificationTokenEntity);
  }
}
