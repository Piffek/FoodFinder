package com.example.foodFinder.Facades;

import com.example.foodFinder.Converters.Converter;
import com.example.foodFinder.Converters.ReverseConverter;
import com.example.foodFinder.Dto.UserDTO;
import com.example.foodFinder.Facades.Interfaces.UserFacade;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import com.example.foodFinder.Services.Interfaces.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeImpl implements UserFacade {

  private final ReverseConverter<UserEntity, UserDTO> reverseConverter;
  private final Converter<UserDTO, UserEntity> converter;
  private final UserService userService;

  public UserFacadeImpl(
      final ReverseConverter<UserEntity, UserDTO> reverseConverter,
      final Converter<UserDTO, UserEntity> converter,
      final UserService userService) {
    this.reverseConverter = reverseConverter;
    this.converter = converter;
    this.userService = userService;
  }

  @Override
  public Long createUser(final UserDTO userDto) {
    final UserEntity userEntity = reverseConverter.convert(userDto, new UserEntity());
    userService.createUser(userEntity);
    return userEntity.getId();
  }

  @Override
  public void updateUser(final UserDTO userDto) {
    final UserEntity userEntity = userService.findById(userDto.getId());
    final UserEntity mappedUser = reverseConverter.convert(userDto, userEntity);
    userService.updateUser(mappedUser);
  }

  @Override
  public UserDTO findByUsername(final String username) {
    final UserEntity userEntity = userService.findByUsername(username);
    return converter.convert(userEntity);
  }

  @Override
  public UserDTO findById(final Long id) {
    return converter.convert(userService.findById(id));
  }
}