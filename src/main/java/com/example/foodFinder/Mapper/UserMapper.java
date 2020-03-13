package com.example.foodFinder.Mapper;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Persistance.Entities.UserEntity;

public class UserMapper {

  public static UserEntityDTO toDto(final UserEntity userEntity) {
    return new UserEntityDTO()
        .setEmailAdress(userEntity.getEmailAdress())
        .setName(userEntity.getName())
        .setCity(userEntity.getCity())
        .setAccountPlan(userEntity.getAccountPlan())
        .setActivatedToken(userEntity.getActivatedToken());
  }

  public static UserEntity toEntity(final UserEntityDTO userEntityDTO) {
    return new UserEntity()
        .setEmailAdress(userEntityDTO.getEmailAdress())
        .setName(userEntityDTO.getName())
        .setCity(userEntityDTO.getCity())
        .setAccountPlan(userEntityDTO.getAccountPlan())
        .setActivatedToken(userEntityDTO.getActivatedToken());
  }
}