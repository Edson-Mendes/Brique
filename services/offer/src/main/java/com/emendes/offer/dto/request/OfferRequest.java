package com.emendes.offer.dto.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@EqualsAndHashCode
public class OfferRequest {

  private BigDecimal value;
  private String status;
  private Long productId;

}
