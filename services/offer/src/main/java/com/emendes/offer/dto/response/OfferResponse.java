package com.emendes.offer.dto.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class OfferResponse {

  private Long id;
  private BigDecimal value;
  private String status;
  private Long productId;

}
