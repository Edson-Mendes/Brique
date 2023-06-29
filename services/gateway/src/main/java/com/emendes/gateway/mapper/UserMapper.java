package com.emendes.gateway.mapper;

import com.emendes.gateway.dto.event.CreateUserEvent;
import com.emendes.gateway.model.entity.User;

/**
 * Interface component com as abstrações de mapeamento de User.
 */
public interface UserMapper {

  /**
   * Mapeia um objeto CreateUserEvent para User.
   * @param createUserEvent contendo as informações do User.
   * @return Entidade User.
   */
  User toUser(CreateUserEvent createUserEvent);

}
