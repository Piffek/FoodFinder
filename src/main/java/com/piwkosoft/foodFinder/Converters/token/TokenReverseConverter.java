package com.piwkosoft.foodFinder.Converters.token;

import com.piwkosoft.foodFinder.Converters.ReverseConverter;
import com.piwkosoft.foodFinder.Dto.VerificationTokenDTO;
import com.piwkosoft.foodFinder.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Persistance.Entities.VerificationTokenEntity;
import com.piwkosoft.foodFinder.Services.Interfaces.UserService;
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
    verificationTokenEntity.setUsed(verificationTokenDTO.isUsed());
    return verificationTokenEntity;
  }
}
