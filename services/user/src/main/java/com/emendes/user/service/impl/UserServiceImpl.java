package com.emendes.user.service.impl;

import com.emendes.user.dto.request.CreateUserRequest;
import com.emendes.user.dto.response.UserResponse;
import com.emendes.user.mapper.UserMapper;
import com.emendes.user.model.entity.User;
import com.emendes.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link UserService}
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  @Override
  public UserResponse register(CreateUserRequest createUserRequest) {
    User user = userMapper.toUser(createUserRequest);
    // TODO: Encriptar o password.

    // TODO: Persistir o novo User no banco de dados.

    return userMapper.toUserResponse(user);
  }

}
