package com.emendes.user.service.impl;

import com.emendes.user.dto.event.CreateUserEvent;
import com.emendes.user.dto.request.CreateUserRequest;
import com.emendes.user.dto.response.UserResponse;
import com.emendes.user.mapper.UserMapper;
import com.emendes.user.model.entity.User;
import com.emendes.user.producer.CreateUserProducer;
import com.emendes.user.repository.UserRepository;
import com.emendes.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
  private final CreateUserProducer createUserProducer;

  @Override
  public UserResponse register(CreateUserRequest createUserRequest) {
    log.info("attempt to register user with email: {}", createUserRequest.email());
    User user = userMapper.toUser(createUserRequest);

    user.setPassword(passwordEncoder.encode(createUserRequest.password()));
    user.setAuthorities("ROLE_USER");

    try {
      // TODO: Descomentar quando for pra persistir no banco de dados.
//      userRepository.save(user);
      log.info("Persisting user [name: {}, email: {}, authorities: {}]", user.getName(), user.getEmail(), user.getAuthorities());
    } catch (DataIntegrityViolationException exception) {
      log.info("fail to persist user! exception message: {}", exception.getMessage());
      throw new ResponseStatusException(
          HttpStatusCode.valueOf(400), String.format("Email {%s} already in use", user.getEmail()));
    }
    log.info("successfully registered user with id: {}", user.getId());

    // TODO: Criar um mapper para o objeto abaixo.
    CreateUserEvent createUserEvent = CreateUserEvent.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .authorities(user.getAuthorities())
        .build();
    createUserProducer.sendMessage(createUserEvent);

    return userMapper.toUserResponse(user);
  }

}
