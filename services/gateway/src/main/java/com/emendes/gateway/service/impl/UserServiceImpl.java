package com.emendes.gateway.service.impl;

import com.emendes.gateway.dto.event.CreateUserEvent;
import com.emendes.gateway.mapper.UserMapper;
import com.emendes.gateway.model.entity.User;
import com.emendes.gateway.repository.UserRepository;
import com.emendes.gateway.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Implementação de {@link UserService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;
  private final UserRepository userRepository;

  @Override
  public Mono<User> save(CreateUserEvent createUserEvent) {
    // TODO: Onde realizar a validação de createUserEvent?

    User user = userMapper.toUser(createUserEvent);

    log.info("mapped user=[username: {}, authorities: {}]", user.getUsername(), user.getAuthorities());

    return userRepository.save(user);
  }

}
