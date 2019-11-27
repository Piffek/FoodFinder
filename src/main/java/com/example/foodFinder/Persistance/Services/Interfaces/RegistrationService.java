package com.example.foodFinder.Persistance.Services.Interfaces;

import com.example.foodFinder.Persistance.Entities.UserEntity;

import java.util.List;

public interface RegistrationService {
    UserEntity customerRegistration(UserEntity userEntity);
    List<UserEntity> multiCustomerRegistration(List<UserEntity> userEntities);
}
