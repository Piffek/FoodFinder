package com.piwkosoft.foodFinder.Converters.token;

import com.piwkosoft.foodFinder.Converters.Converter;
import com.piwkosoft.foodFinder.Dto.VerificationTokenDTO;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.VerificationTokenEntity;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 17.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class TokenConverter implements Converter<VerificationTokenDTO, VerificationTokenEntity> {

  @Override
  public VerificationTokenDTO convert(VerificationTokenEntity verificationTokenEntity) {
    return new VerificationTokenDTO()
        .setTokenExpiryDate(verificationTokenEntity.getTokenExpiryDate())
        .setToken(verificationTokenEntity.getToken())
        .setId(verificationTokenEntity.getId())
        .setUsed(verificationTokenEntity.isUsed())
        .setUser(verificationTokenEntity.getUser() != null ? verificationTokenEntity.getUser().getId() : null);
  }
}
