package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Services.AccountServiceImpl;

import java.util.List;

public interface RegistrationService {
    Long createUser(UserEntityDTO userEntityDto);
}
