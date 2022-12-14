package com.emendes.product.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductResponse {

  private String name;
  private String description;

}
