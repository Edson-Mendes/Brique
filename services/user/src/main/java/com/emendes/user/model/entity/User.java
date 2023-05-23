package com.emendes.user.model.entity;

import lombok.*;

/**
 * Entidade JPA User, refere-se a tabela t_user no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {

  private Long id;
  private String name;
  // TODO: Deve ser unique
  private String email;
  private String password;

}
