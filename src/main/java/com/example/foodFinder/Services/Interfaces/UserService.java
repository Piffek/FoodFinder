package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Persistance.Entities.RoleEntity;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import java.util.List;
import java.util.Set;

public interface UserService {
    Long createUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    UserEntity findByUsername(String username);
    UserEntity findById(Long id);
    List<RoleEntity> getRolesByUsername(String username);
}
