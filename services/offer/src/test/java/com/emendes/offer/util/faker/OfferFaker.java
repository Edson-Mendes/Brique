package com.emendes.offer.util.faker;

import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.model.entity.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.emendes.offer.util.ConstantsUtil.PAGEABLE_DEFAULT;
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

  /**
   * Fornece uma instância de Page<OfferResponse> para uso em testes automatizados.
   * @return
   */
  public static Page<Offer> offerPage() {
    return new PageImpl<>(List.of(offer()), PAGEABLE_DEFAULT, 1);
  }

}
