package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

import com.piwkosoft.foodFinder.Dto.UserDTO;

public interface UserFacade {
    Long createUser(UserDTO userDto);
    void updateUser(UserDTO userDto);
    void setAsEnable(UserDTO userDto);
    UserDTO findByUsername(String username);
    UserDTO findById(Long id);
}
