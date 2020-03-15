package com.example.foodFinder.Services;

import com.example.foodFinder.Mapper.UserMapper;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.UserService;
import com.example.foodFinder.Dto.UserEntityDTO;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long createUser(final UserEntityDTO userEntityDto) {
        final UserEntity userEntity = UserMapper.toEntity(userEntityDto);
        em.persist(userEntity);
        return userEntity.getId();
    }

  @Override
  public void updateUser(final UserEntityDTO userEntityDto) {
    final UserEntity userEntity = UserMapper.toEntity(userEntityDto);
    em.merge(userEntity);
  }
}
