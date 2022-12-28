package com.emendes.offer.dto.request;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class OfferRequest {

  @NotNull(message = "value must not be null")
  @Digits(integer = 7, fraction = 2, message = "numerical value out of range ({integer} digits.{fraction} digits)")
  @Positive(message = "value must be bigger than 0")
  private BigDecimal value;
  @NotNull(message = "productId must not be null")
  @Positive(message = "productId must be bigger than 0")
  private Long productId;

}
