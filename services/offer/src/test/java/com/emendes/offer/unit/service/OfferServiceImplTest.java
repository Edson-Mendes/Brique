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
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;

import static com.emendes.offer.util.ConstantsUtil.PAGEABLE_DEFAULT;
import static com.emendes.offer.util.faker.OfferFaker.*;
import static com.emendes.offer.util.faker.ProductFaker.productResponse;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests for OfferServiceImpl")
class OfferServiceImplTest {

  @InjectMocks
  private OfferServiceImpl offerService;
  @Mock
  private OfferRepository offerRepositoryMock;
  @Mock
  private OfferMapper offerMapperMock;
  @Mock
  private ProductService productServiceMock;

  @Nested
  @DisplayName("Unit tests for makeOffer method")
  class MakeOfferMethod {

    @Test
    @DisplayName("makeOffer must return OfferResponse when makeOffer successfully")
    void makeOffer_MustReturnOfferResponse_WhenMakeOfferSuccessfully() {
      BDDMockito.when(productServiceMock.findAvailableProductById(any())).thenReturn(productResponse());
      BDDMockito.when(offerMapperMock.toOffer(any())).thenReturn(offerWithoutIdAndCreatedAt());
      BDDMockito.when(offerRepositoryMock.save(any())).thenReturn(offer());
      BDDMockito.when(offerMapperMock.toOfferResponse(any(), any())).thenReturn(offerResponse());

      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal("250.00"))
          .productId(5_555_555L)
          .build();

      OfferResponse actualOfferResponse = offerService.makeOffer(offerRequest);

      BDDMockito.verify(productServiceMock).findAvailableProductById(any());
      BDDMockito.verify(offerMapperMock).toOffer(any());
      BDDMockito.verify(offerRepositoryMock).save(any());
      BDDMockito.verify(offerMapperMock).toOfferResponse(any(), any());

      Assertions.assertThat(actualOfferResponse).isNotNull();
      Assertions.assertThat(actualOfferResponse.id()).isNotNull().isEqualTo(100_000L);
      Assertions.assertThat(actualOfferResponse.status()).isNotNull().isEqualTo("WAITING");
      Assertions.assertThat(actualOfferResponse.value()).isNotNull().isEqualTo("250.00");
      Assertions.assertThat(actualOfferResponse.product().id()).isNotNull().isEqualTo(5_555_555L);
    }

    @Test
    @DisplayName("makeOffer must throws InvalidOfferException when productId do not match with any product")
    void makeOffer_MustThrowsInvalidOfferException_WhenProductIdDoNotMatchWithAnyProduct() {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal("250.00"))
          .productId(9999999L)
          .build();

      BDDMockito.doThrow(new InvalidOfferException("The specified product was not found", HttpStatus.BAD_REQUEST))
          .when(productServiceMock).findAvailableProductById(9_999_999L);

      Assertions.assertThatExceptionOfType(InvalidOfferException.class)
          .isThrownBy(() -> offerService.makeOffer(offerRequest))
          .withMessage("The specified product was not found");

      BDDMockito.verify(productServiceMock).findAvailableProductById(9_999_999L);
    }

  }

  @Nested
  @DisplayName("Unit tests for fetchAll method")
  class FetchAllMethod {

    @Test
    @DisplayName("fetchAll must return Page<OfferResponse> when fetch all successfully")
    void fetchAll_MustReturnPageOfferResponse_WhenFetchAllSuccessfully() {
      BDDMockito.when(offerRepositoryMock.findAll(any(Pageable.class)))
          .thenReturn(offerPage());
      BDDMockito.when(offerMapperMock.toOfferResponse(any(Offer.class)))
          .thenReturn(offerResponse());

      Page<OfferResponse> offerResponsePage = offerService.fetchAll(PAGEABLE_DEFAULT);

      Assertions.assertThat(offerResponsePage).isNotNull().hasSize(1);

      BDDMockito.verify(offerRepositoryMock).findAll(any(Pageable.class));
      BDDMockito.verify(offerMapperMock).toOfferResponse(any(Offer.class));
    }

  }

}