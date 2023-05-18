package com.emendes.offer.util.faker;

import com.emendes.offer.dto.response.ProductResponse;

import java.math.BigDecimal;

public class ProductFaker {

  private ProductFaker(){}

  /**
   * Fornece uma instância de ProductResponse para uso em testes automatizados.
   */
  public static ProductResponse productResponse() {
    return ProductResponse.builder()
        .id(5555555L)
        .name("Product XPTO")
        .description("Description XPTO")
        .price(new BigDecimal("300.00"))
        .build();
  }

}
