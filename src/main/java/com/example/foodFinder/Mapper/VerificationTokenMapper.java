package com.example.foodFinder.Mapper;

import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Persistance.Entities.VerificationTokenEntity;
import javax.jws.soap.SOAPBinding.Use;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 13.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public class VerificationTokenMapper {

  public static VerificationTokenDTO toDTO(final VerificationTokenEntity verificationTokenEntity) {
    VerificationTokenDTO verificationTokenDTO = new VerificationTokenDTO();
    verificationTokenDTO.setId(verificationTokenEntity.getId());
    verificationTokenDTO.setToken(verificationTokenEntity.getToken());
    verificationTokenDTO.setTokenExpiryDate(verificationTokenEntity.getTokenExpiryDate());
    verificationTokenDTO.setUser(verificationTokenEntity.getUser() != null ? UserMapper
        .toDto(verificationTokenEntity.getUser()) : null);
    return verificationTokenDTO;
  }

  public static VerificationTokenEntity toEntity(final VerificationTokenDTO verificationTokenDTO) {
    VerificationTokenEntity verificationTokenEntity = new VerificationTokenEntity();
    verificationTokenEntity.setId(verificationTokenDTO.getId());
    verificationTokenEntity.setToken(verificationTokenDTO.getToken());
    verificationTokenEntity.setTokenExpiryDate(verificationTokenDTO.getTokenExpiryDate());
    return verificationTokenEntity;
  }
}
