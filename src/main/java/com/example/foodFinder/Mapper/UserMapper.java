package com.example.foodFinder.Mapper;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Persistance.Entities.UserEntity;

public class UserMapper {

    public UserEntityDTO toUserEntityDto(UserEntity userEntity) {
        UserEntityDTO userEntityDTO = new UserEntityDTO();
        userEntityDTO.setAccountType(userEntity.getAccountType());
        userEntityDTO.setCity(userEntity.getCity());
        userEntityDTO.setStreet(userEntity.getStreet());
        userEntityDTO.setId(userEntity.getId());
        userEntityDTO.setNuisanceEntitySet(userEntity.getNuisanceEntitySet());
        return userEntityDTO;
    }
}
