package com.emendes.product.dto.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class ProductRequest {

  private String name;
  private String description;
  private BigDecimal price;

}
