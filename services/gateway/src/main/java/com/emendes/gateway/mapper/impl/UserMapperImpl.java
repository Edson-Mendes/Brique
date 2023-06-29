package com.emendes.gateway.mapper.impl;

import com.emendes.gateway.dto.event.CreateUserEvent;
import com.emendes.gateway.mapper.UserMapper;
import com.emendes.gateway.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public User toUser(CreateUserEvent createUserEvent) {
    return User.builder()
        .username(createUserEvent.username())
        .password(createUserEvent.password())
        .authorities(createUserEvent.authorities())
        .build();
  }

}
