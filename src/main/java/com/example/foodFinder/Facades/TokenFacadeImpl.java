package com.example.foodFinder.Facades;

import com.example.foodFinder.Converters.Converter;
import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Facades.Interfaces.TokenFacade;
import com.example.foodFinder.Persistance.Entities.VerificationTokenEntity;
import com.example.foodFinder.Services.Interfaces.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 13.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class TokenFacadeImpl implements TokenFacade {
  private static final Logger logger = LoggerFactory.getLogger(
      TokenFacadeImpl.class);

  private final TokenService tokenService;
  private final Converter<VerificationTokenDTO, VerificationTokenEntity> converter;

  public TokenFacadeImpl(final TokenService tokenService,
      Converter<VerificationTokenDTO, VerificationTokenEntity> converter) {
    this.tokenService = tokenService;
    this.converter = converter;
  }

  @Override
  public VerificationTokenDTO generateSecretToken() {
    VerificationTokenEntity verificationTokenEntity = tokenService.generateSecretToken();

    logger.debug("create token {}", verificationTokenEntity.getToken());
    return converter.convert(verificationTokenEntity);
  }

  @Override
  public VerificationTokenDTO getByToken(final String token) {
    VerificationTokenEntity verificationTokenEntity = tokenService.getByToken(token);
    if(verificationTokenEntity == null) {
      return null;
    }

    return converter.convert(verificationTokenEntity);
  }
}
