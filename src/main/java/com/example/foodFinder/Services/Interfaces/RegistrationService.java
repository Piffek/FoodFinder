package com.example.foodFinder.Services.Interfaces;

import com.example.foodFinder.Dto.UserEntityDTO;

import java.util.List;

public interface RegistrationService {
    UserEntityDTO userRegistration(UserEntityDTO userEntityDto);
    List<UserEntityDTO> multiCustomerRegistration(List<UserEntityDTO> userEntitiesDto);
}
