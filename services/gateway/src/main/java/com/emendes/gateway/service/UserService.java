package com.emendes.gateway.service;

import com.emendes.gateway.dto.event.CreateUserEvent;

/**
 * Interface service com as abstrações para manipulação de User.
 */
public interface UserService {

  /**
   * Salva as informações básicas do usuário para autenticação.
   * @param createUserEvent contendo as informações username, password (criptografado) e authorities do usuário.
   */
  void save(CreateUserEvent createUserEvent);

}
