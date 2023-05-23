package com.emendes.offer.unit.mapper;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.dto.response.ProductResponse;
import com.emendes.offer.mapper.OfferMapper;
import com.emendes.offer.mapper.impl.OfferMapperImpl;
import com.emendes.offer.model.entity.Offer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DisplayName("Unit tests for OfferMapperImpl")
class OfferMapperImplTest {

  private OfferMapper offerMapper;

  @BeforeEach
  void setUp() {
    offerMapper = new OfferMapperImpl();
  }

  @Test
  @DisplayName("toOffer must return Offer when map successfully")
  void toOffer_MustReturnOffer_WhenMapSuccessfully() {
    OfferRequest offerRequestToBeMapped = OfferRequest.builder()
        .value(new BigDecimal("250.00"))
        .productId(999L)
        .build();

    Offer actualOffer = offerMapper.toOffer(offerRequestToBeMapped);

    Assertions.assertThat(actualOffer).isNotNull();
    Assertions.assertThat(actualOffer.getId()).isNull();
    Assertions.assertThat(actualOffer.getCreatedAt()).isNull();
    Assertions.assertThat(actualOffer.getStatus()).isNull();
    Assertions.assertThat(actualOffer.getValue()).isNotNull().isEqualTo("250.00");
    Assertions.assertThat(actualOffer.getProductId()).isNotNull().isEqualTo(999L);
  }

  @Test
  @DisplayName("toOfferResponse must return OfferResponse when map successfully")
  void toOfferResponse_MustReturnOfferResponse_WhenMapSuccessfully() {
    Offer offerToBeMapped = Offer.builder()
        .id(100_000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .productId(5_555_555L)
        .createdAt(LocalDateTime.parse("2022-12-10T10:00:00"))
        .build();

    ProductResponse productResponse = ProductResponse.builder()
        .id(5_555_555L)
        .name("Product XPTO")
        .description("Description XPTO")
        .price(new BigDecimal("350.00"))
        .build();

    OfferResponse actualOfferResponse = offerMapper.toOfferResponse(offerToBeMapped, productResponse);

    Assertions.assertThat(actualOfferResponse).isNotNull();
    Assertions.assertThat(actualOfferResponse.id()).isNotNull().isEqualTo(100_000L);
    Assertions.assertThat(actualOfferResponse.value()).isNotNull().isEqualTo("250.00");
    Assertions.assertThat(actualOfferResponse.status()).isNotNull().isEqualTo("WAITING");
    Assertions.assertThat(actualOfferResponse.product().id()).isNotNull().isEqualTo(5_555_555L);
    Assertions.assertThat(actualOfferResponse.product().name()).isNotNull().isEqualTo("Product XPTO");
    Assertions.assertThat(actualOfferResponse.product().description()).isNotNull().isEqualTo("Description XPTO");
    Assertions.assertThat(actualOfferResponse.product().price()).isNotNull().isEqualTo("350.00");
  }

  @Test
  @DisplayName("toOfferResponse must return OfferResponse when map successfully with only Offer")
  void toOfferResponse_MustReturnOfferResponse_WhenMapSuccessfullyWithOnlyOffer() {
    Offer offerToBeMapped = Offer.builder()
        .id(100_000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .productId(5_555_555L)
        .createdAt(LocalDateTime.parse("2022-12-10T10:00:00"))
        .build();

    OfferResponse actualOfferResponse = offerMapper.toOfferResponse(offerToBeMapped);

    Assertions.assertThat(actualOfferResponse).isNotNull();
    Assertions.assertThat(actualOfferResponse.id()).isNotNull().isEqualTo(100_000L);
    Assertions.assertThat(actualOfferResponse.value()).isNotNull().isEqualTo("250.00");
    Assertions.assertThat(actualOfferResponse.status()).isNotNull().isEqualTo("WAITING");
    Assertions.assertThat(actualOfferResponse.product().id()).isNotNull().isEqualTo(5_555_555L);
    Assertions.assertThat(actualOfferResponse.product().name()).isNull();
    Assertions.assertThat(actualOfferResponse.product().description()).isNull();
    Assertions.assertThat(actualOfferResponse.product().price()).isNull();
  }

}