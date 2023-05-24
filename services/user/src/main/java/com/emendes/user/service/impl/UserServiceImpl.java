package com.emendes.user.service.impl;

import com.emendes.user.dto.request.CreateUserRequest;
import com.emendes.user.dto.response.UserResponse;
import com.emendes.user.mapper.UserMapper;
import com.emendes.user.model.entity.User;
import com.emendes.user.repository.UserRepository;
import com.emendes.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link UserService}
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Override
  public UserResponse register(CreateUserRequest createUserRequest) {
    User user = userMapper.toUser(createUserRequest);

    user.setPassword(passwordEncoder.encode(createUserRequest.password()));
    userRepository.save(user);
    log.info("successfully registered user with id: {}", user.getId());

    return userMapper.toUserResponse(user);
  }

}
