package com.emendes.offer.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
  private LocalDateTime createdAt;

}
