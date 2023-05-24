package com.emendes.user.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade JPA User, refere-se a tabela t_user no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "t_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(length = 100)
  private String name;
  @Column(length = 150, unique = true)
  private String email;
  private String password;

}
