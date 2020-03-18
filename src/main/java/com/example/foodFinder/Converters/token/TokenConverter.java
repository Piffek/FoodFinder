package com.example.foodFinder.Converters.token;

import com.example.foodFinder.Converters.Converter;
import com.example.foodFinder.Dto.UserDTO;
import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Persistance.Entities.VerificationTokenEntity;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
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
        .setUser(verificationTokenEntity.getUser() != null ? verificationTokenEntity.getUser().getId() : null);
  }
}
