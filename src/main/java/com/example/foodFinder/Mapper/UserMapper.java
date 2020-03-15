package com.example.foodFinder.Mapper;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Persistance.Entities.UserEntity;

public class UserMapper {

  public static UserEntityDTO toDto(final UserEntity userEntity) {
    return new UserEntityDTO()
        .setId(userEntity.getId())
        .setPassword(userEntity.getPassword())
        .setEmailAdress(userEntity.getEmailAdress())
        .setName(userEntity.getName())
        .setCity(userEntity.getCity())
        .setAccountPlan(userEntity.getAccountPlan())
        .setActivatedToken(userEntity.getActivatedToken())
        .setEnabled(userEntity.isEnabled())
        .setCreatedDate(userEntity.getCreatedDate())
        .setUpdatedDate(userEntity.getUpdatedDate());
  }

  public static UserEntity toEntity(final UserEntityDTO userEntityDTO) {
    return new UserEntity()
        .setId(userEntityDTO.getId())
        .setPassword(userEntityDTO.getPassword())
        .setEmailAdress(userEntityDTO.getEmailAdress())
        .setName(userEntityDTO.getName())
        .setCity(userEntityDTO.getCity())
        .setAccountPlan(userEntityDTO.getAccountPlan())
        .setActivatedToken(userEntityDTO.getActivatedToken())
        .setEnabled(userEntityDTO.isEnabled())
        .setCreatedDate(userEntityDTO.getCreatedDate())
        .setUpdatedDate(userEntityDTO.getUpdatedDate());
  }
}