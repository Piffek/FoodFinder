package com.example.foodFinder.Facades.Interfaces;

import com.example.foodFinder.Dto.UserDTO;

public interface UserFacade {
    Long createUser(UserDTO userDto);
    void updateUser(UserDTO userDto);
    UserDTO findByUsername(String username);
    UserDTO findById(Long id);
}
