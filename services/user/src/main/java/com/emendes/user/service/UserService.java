package com.emendes.user.service;

import com.emendes.user.dto.request.CreateUserRequest;
import com.emendes.user.dto.response.UserResponse;

/**
 * Interface Service que contém as abstrações que manipulam o recurso User.
 */
public interface UserService {

  /**
   * Registra um User no sistema.
   *
   * @param createUserRequest contém os dados do User que será salvo.
   * @return UserResponse contendo id, name e email do User.
   */
  UserResponse register(CreateUserRequest createUserRequest);

}
