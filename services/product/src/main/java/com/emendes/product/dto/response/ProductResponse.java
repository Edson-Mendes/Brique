package com.emendes.product.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductResponse {

  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
  private LocalDateTime createdAt;

}
