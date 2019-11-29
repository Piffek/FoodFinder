package com.example.foodFinder.Persistance.Services.Interfaces;

import com.example.foodFinder.Persistance.dto.UserEntityDTO;

import java.util.List;

public interface RegistrationService {
    UserEntityDTO userRegistration(UserEntityDTO userEntity);
    List<UserEntityDTO> multiCustomerRegistration(List<UserEntityDTO> userEntities);
}
