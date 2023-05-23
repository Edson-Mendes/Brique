package com.emendes.user.mapper;

import com.emendes.user.dto.request.CreateUserRequest;
import com.emendes.user.dto.response.UserResponse;
import com.emendes.user.model.entity.User;

/**
 * Interface component que contém as abstrações de mapeamento de DTOs para a entidade User e vice-versa.
 */
public interface UserMapper {

  /**
   * Mapeia o DTO CreateUserRequest para a entidade User.
   * @param createUserRequest que deve ser mapeado para User
   * @return Task com dados vindo de createUserRequest.
   */
  User toUser(CreateUserRequest createUserRequest);

  UserResponse toUserResponse(User user);

}
