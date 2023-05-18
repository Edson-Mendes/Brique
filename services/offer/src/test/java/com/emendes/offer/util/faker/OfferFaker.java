package com.emendes.offer.util.faker;

import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.model.entity.Offer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.emendes.offer.util.faker.ProductFaker.productResponse;

public class OfferFaker {

  private OfferFaker() {}

  /**
   * Fornece uma instância de Offer para uso em testes automatizados.
   */
  public static Offer offer() {
    return Offer.builder()
        .id(100_000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .createdAt(LocalDateTime.parse("2022-12-10T10:00:00"))
        .build();
  }

  /**
   * Fornece uma instância de Offer sem id e createdAt para uso em testes automatizados.
   */
  public static Offer offerWithoutIdAndCreatedAt() {
    return Offer.builder()
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .productId(5555555L)
        .build();
  }

  /**
   * Fornece uma instância de OfferResponse para uso em testes automatizados.
   */
  public static OfferResponse offerResponse() {
    return OfferResponse.builder()
        .id(100000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .product(productResponse())
        .build();
  }

}
