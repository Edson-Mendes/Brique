package com.emendes.user.mapper.impl;

import com.emendes.user.dto.request.CreateUserRequest;
import com.emendes.user.dto.response.UserResponse;
import com.emendes.user.mapper.UserMapper;
import com.emendes.user.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * Implementação de {@link UserMapper}
 */
@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public User toUser(CreateUserRequest createUserRequest) {
    return User.builder()
        .name(createUserRequest.name())
        .email(createUserRequest.email())
        .build();
  }

  @Override
  public UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }

}
