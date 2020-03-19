package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Persistance.Entities.UserEntity;

public interface UserService {
    Long createUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    UserEntity findByUsername(String username);
    UserEntity findById(Long id);
}
