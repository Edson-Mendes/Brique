package com.emendes.offer.unit.mapper;

import com.emendes.offer.config.bean.ModelMapperConfig;
import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
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
    offerMapper = new OfferMapperImpl(new ModelMapperConfig().modelMapper());
  }

  @Test
  @DisplayName("toOffer must return Offer when map successfully")
  void toOffer_MustReturnOffer_WhenMapSuccessfully() {
    OfferRequest offerRequestToBeMapped = OfferRequest.builder()
        .value(new BigDecimal("250.00"))
        .productId(999L)
        .status("WAITING")
        .build();

    Offer actualOffer = offerMapper.toOffer(offerRequestToBeMapped);

    Assertions.assertThat(actualOffer).isNotNull();
    Assertions.assertThat(actualOffer.getId()).isNull();
    Assertions.assertThat(actualOffer.getCreatedAt()).isNull();
    Assertions.assertThat(actualOffer.getValue()).isNotNull().isEqualTo("250.00");
    Assertions.assertThat(actualOffer.getProductId()).isNotNull().isEqualTo(999L);
    Assertions.assertThat(actualOffer.getStatus()).isNotNull().isEqualTo("WAITING");
  }

  @Test
  @DisplayName("toOfferResponse must return OfferResponse when map successfully")
  void toOfferResponse_MustReturnOfferResponse_WhenMapSuccessfully() {
    Offer offerToBeMapped = Offer.builder()
        .id(100000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .productId(999L)
        .createdAt(LocalDateTime.parse("2022-12-10T10:00:00"))
        .build();

    OfferResponse actualOfferResponse = offerMapper.toOfferResponse(offerToBeMapped);
    OfferResponse expectedOfferResponse = OfferResponse.builder()
        .id(100000L)
        .value(new BigDecimal("250.00"))
        .productId(999L)
        .status("WAITING")
        .build();

    Assertions.assertThat(actualOfferResponse).isNotNull().isEqualTo(expectedOfferResponse);
  }

}