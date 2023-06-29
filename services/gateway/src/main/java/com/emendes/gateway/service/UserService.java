package com.emendes.gateway.service;

import com.emendes.gateway.dto.event.CreateUserEvent;
import com.emendes.gateway.model.entity.User;
import reactor.core.publisher.Mono;

/**
 * Interface service com as abstrações para manipulação de User.
 */
public interface UserService {

  /**
   * Salva as informações básicas do usuário para autenticação.
   *
   * @param createUserEvent contendo as informações username, password (criptografado) e authorities do usuário.
   */
  Mono<User> save(CreateUserEvent createUserEvent);

}
