package com.example.foodFinder.Services;

import com.example.foodFinder.Mapper.UserMapper;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.RegistrationService;
import com.example.foodFinder.Dto.UserEntityDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Long createUser(UserEntityDTO userEntityDto) {
        UserEntity userEntity = UserMapper.toEntity(userEntityDto);
        em.persist(userEntity);
        return userEntity.getId();
    }
}
