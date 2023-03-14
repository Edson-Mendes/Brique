package com.emendes.offer.dto.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class ProductResponse {
//  TODO: A mesma classe está no serviço product
//  Pesquisar as maneiras de fazer esse compartilhamento de classes
//  Manter independentes? Criar um módulo com essas classes compartilhadas?
  private Long id;
  private String name;
  private String description;
  private BigDecimal price;

}
