package com.example.foodFinder.Services;

import com.example.foodFinder.Mapper.UserMapper;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.RegistrationService;
import com.example.foodFinder.Dto.UserEntityDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public UserEntityDTO userRegistration(UserEntityDTO userEntityDto) {
        UserEntity userEntity = UserMapper.toEntity(userEntityDto);
        em.persist(userEntity);
        em.flush();
        return userEntityDto;
    }

    @Override
    public List<UserEntityDTO> multiCustomerRegistration(List<UserEntityDTO> userEntities) {
        return null;
    }
}
