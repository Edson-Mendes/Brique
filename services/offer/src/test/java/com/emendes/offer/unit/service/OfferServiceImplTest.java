package com.emendes.offer.unit.service;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.exception.InvalidOfferException;
import com.emendes.offer.mapper.OfferMapper;
import com.emendes.offer.model.entity.Offer;
import com.emendes.offer.repository.OfferRepository;
import com.emendes.offer.service.ProductService;
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
import org.springframework.http.HttpStatus;
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
  @Mock
  private ProductService productServiceMock;

  @Nested
  @DisplayName("Unit tests for save method")
  class SaveMethod {

    @Test
    @DisplayName("save must return OfferResponse when save successfully")
    void save_MustReturnOfferResponse_WhenSaveSuccessfully() {
      Offer offerWithoutIdAndCreatedAt = Offer.builder()
          .value(new BigDecimal("250.00"))
          .status("WAITING")
          .productId(5555555L)
          .build();
      BDDMockito.when(mapperMock.toOffer(ArgumentMatchers.any())).thenReturn(offerWithoutIdAndCreatedAt);
      BDDMockito.when(offerRepositoryMock.save(ArgumentMatchers.any())).thenReturn(offer());
      BDDMockito.when(mapperMock.toOfferResponse(ArgumentMatchers.any())).thenReturn(offerResponse());

      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal("250.00"))
          .productId(5555555L)
          .build();

      OfferResponse actualOfferResponse = offerService.save(offerRequest);
      BDDMockito.verify(productServiceMock).isAvailable(5555555L);
      BDDMockito.verify(mapperMock).toOffer(ArgumentMatchers.any());
      BDDMockito.verify(offerRepositoryMock).save(ArgumentMatchers.any());
      BDDMockito.verify(mapperMock).toOfferResponse(ArgumentMatchers.any());

      OfferResponse expectedOfferResponse = OfferResponse.builder()
          .id(100000L)
          .value(new BigDecimal("250.00"))
          .status("WAITING")
          .productId(5555555L)
          .build();

      Assertions.assertThat(actualOfferResponse).isNotNull().isEqualTo(expectedOfferResponse);
    }

    @Test
    @DisplayName("save must throws InvalidOfferException when productId do not match with any product")
    void save_MustThrowsInvalidOfferException_WhenProductIdDoNotMatchWithAnyProduct() {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal("250.00"))
          .productId(9999999L)
          .build();

      BDDMockito.doThrow(new InvalidOfferException("The specified product was not found", HttpStatus.BAD_REQUEST))
          .when(productServiceMock).isAvailable(9999999L);

      Assertions.assertThatExceptionOfType(InvalidOfferException.class)
          .isThrownBy(() -> offerService.save(offerRequest))
          .withMessage("The specified product was not found");
      BDDMockito.verify(productServiceMock).isAvailable(9999999L);
    }

  }

  private OfferResponse offerResponse() {
    return OfferResponse.builder()
        .id(100000L)
        .value(new BigDecimal("250.00"))
        .status("WAITING")
        .productId(5555555L)
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