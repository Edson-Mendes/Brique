package com.emendes.offer.unit.service;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.mapper.OfferMapper;
import com.emendes.offer.model.entity.Offer;
import com.emendes.offer.repository.OfferRepository;
import com.emendes.offer.service.impl.OfferServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests for OfferServiceImpl")
class OfferServiceImplTest {

  @InjectMocks
  private OfferServiceImpl offerService;
  @Mock
  private OfferRepository offerRepositoryMock;
  @Mock
  private OfferMapper mapperMock;

  @Nested
  @DisplayName("Unit tests for save method")
  class SaveMethod {

    @Test
    @DisplayName("save must return OfferResponse when save successfully")
    void save_MustReturnOfferResponse_WhenSaveSuccessfully() {
      Offer offerWithoutIdCreatedAt = Offer.builder()
          .value(new BigDecimal("250.00"))
          .status("WAITING")
          .build();
      BDDMockito.when(mapperMock.toOffer(ArgumentMatchers.any())).thenReturn(offerWithoutIdCreatedAt);
      BDDMockito.when(offerRepositoryMock.save(ArgumentMatchers.any())).thenReturn(offer());
      BDDMockito.when(mapperMock.toOfferResponse(ArgumentMatchers.any())).thenReturn(offerResponse());

      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal("250.00"))
          .status("WAITING")
          .build();

      OfferResponse actualOfferResponse = offerService.save(offerRequest);
      BDDMockito.verify(mapperMock).toOffer(ArgumentMatchers.any());
      BDDMockito.verify(offerRepositoryMock).save(ArgumentMatchers.any());
      BDDMockito.verify(mapperMock).toOfferResponse(ArgumentMatchers.any());

      OfferResponse expectedOfferResponse = OfferResponse.builder()
          .id(100000L)
          .value(new BigDecimal("250.00"))
          .status("WAITING")
          .build();

      Assertions.assertThat(actualOfferResponse).isNotNull().isEqualTo(expectedOfferResponse);
    }

  }

  private OfferResponse offerResponse() {
    return OfferResponse.builder()
        .id(100000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .build();
  }

  private Offer offer() {
    return Offer.builder()
        .id(100000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .createdAt(LocalDateTime.parse("2022-12-10T10:00:00"))
        .build();
  }

}