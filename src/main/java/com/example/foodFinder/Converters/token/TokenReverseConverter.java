package com.example.foodFinder.Converters.token;

import com.example.foodFinder.Converters.ReverseConverter;
import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Persistance.Entities.VerificationTokenEntity;
import com.example.foodFinder.Services.Interfaces.UserService;
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
public class TokenReverseConverter implements ReverseConverter<VerificationTokenEntity, VerificationTokenDTO> {

  private final UserService userService;

  public TokenReverseConverter(final UserService userService) {
    this.userService = userService;
  }

  @Override
  public VerificationTokenEntity convert(VerificationTokenDTO verificationTokenDTO,
      VerificationTokenEntity verificationTokenEntity) {
    final UserEntity userEntity = userService.findById(verificationTokenDTO.getId());

    verificationTokenEntity.setId(verificationTokenDTO.getId());
    verificationTokenEntity.setToken(verificationTokenDTO.getToken());
    verificationTokenEntity.setTokenExpiryDate(verificationTokenDTO.getTokenExpiryDate());
    verificationTokenEntity.setUser(userEntity);
    return verificationTokenEntity;
  }
}
