package com.example.foodFinder.Mapper;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Persistance.Entities.UserEntity;

public class UserMapper {

    public UserEntityDTO toUserEntityDto(UserEntity userEntity) {
        UserEntityDTO userEntityDTO = new UserEntityDTO();
        userEntityDTO.setName(userEntity.getName());
        userEntityDTO.setEmailAdress(userEntity.getEmailAdress());
        userEntityDTO.setPassword(userEntity.getPassword());
        userEntityDTO.setCity(userEntity.getCity());
        userEntityDTO.setAccountPlan(userEntity.getAccountPlan());
        userEntityDTO.setId(userEntity.getId());
        userEntityDTO.setNuisanceEntitySet(userEntity.getNuisanceEntitySet());
        userEntityDTO.setCreatedDate(userEntity.getCreatedDate());
        userEntityDTO.setUpdatedDate(userEntity.getUpdatedDate());
        return userEntityDTO;
    }
}
