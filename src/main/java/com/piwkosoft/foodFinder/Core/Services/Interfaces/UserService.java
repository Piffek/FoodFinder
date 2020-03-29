package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.RoleEntity;
import com.piwkosoft.foodFinder.Core.Persistance.Entities.UserEntity;
import java.util.List;

public interface UserService {
    Long createUser(UserEntity userEntity);
    void updateUser(UserEntity userEntity);
    UserEntity findByUsername(String username);
    UserEntity findById(Long id);
    List<RoleEntity> getRolesByUsername(String username);
}
