package com.emendes.offer.model.entity;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidade JPA Offer, refere-se a tabela t_offer no banco de dados.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "t_offer")
public class Offer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private BigDecimal value;
  @Column(nullable = false)
  private String status;
  @Column(nullable = false)
  private Long productId;
  @Column(nullable = false)
  private LocalDateTime createdAt;

}
