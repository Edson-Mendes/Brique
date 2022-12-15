package com.emendes.product.dto.request;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class ProductRequest {

  @NotBlank(message = "name must not be null or blank")
  @Size(max = 100, message = "name must be maximum {max} characters long")
  private String name;
  @NotBlank(message = "description must not be null or blank")
  @Size(max = 255, message = "description must be maximum {max} characters long")
  private String description;
  @NotNull(message = "price must not be null")
  @Digits(integer = 7, fraction = 2, message = "numerical value out of range ({integer} digits.{fraction} digits)")
  private BigDecimal price;

}
