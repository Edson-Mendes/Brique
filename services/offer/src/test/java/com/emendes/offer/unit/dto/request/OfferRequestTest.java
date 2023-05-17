package com.emendes.offer.unit.dto.request;

import com.emendes.offer.dto.request.OfferRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@DisplayName("Unit tests for OfferRequest")
class OfferRequestTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private final BigDecimal VALID_VALUE = new BigDecimal("200.00");
  private final Long VALID_PRODUCT_ID = 1000L;

  @Nested
  @DisplayName("Tests for value validation")
  class ValueValidation {

    @ParameterizedTest
    @ValueSource(strings = {"9999999.99", "0.01", "1.00", "100.1", "100"})
    @DisplayName("Validate value must not return violations when value is valid")
    void validateValue_MustNotReturnViolations_WhenValueIsValid(String value) {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal(value))
          .productId(VALID_PRODUCT_ID)
          .build();

      Set<ConstraintViolation<OfferRequest>> actualViolations = validator.validateProperty(
          offerRequest, "value");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @Test
    @DisplayName("Validate value must return violations when value is null")
    void validateValue_MustReturnViolations_WhenValueIsNull() {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(null)
          .productId(VALID_PRODUCT_ID)
          .build();

      Set<ConstraintViolation<OfferRequest>> actualViolations = validator.validateProperty(
          offerRequest, "value");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("value must not be null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"99999999.99", "0.001"})
    @DisplayName("Validate value must return violations when the number of digits is invalid")
    void validateValue_MustReturnViolations_WhenTheNumberOfDigitsIsInvalid(String invalidValue) {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal(invalidValue))
          .productId(VALID_PRODUCT_ID)
          .build();

      Set<ConstraintViolation<OfferRequest>> actualViolations = validator.validateProperty(
          offerRequest, "value");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("value must contains max 7 integer digits and max 2 fraction digits, e.g. 290.98");
    }

    @ParameterizedTest
    @ValueSource(strings = {"0.00", "-100.00", "-1000000.00"})
    @DisplayName("Validate value must return violations when value is non positive")
    void validateValue_MustReturnViolations_WhenValueIsNonPositive(String nonPositiveValue) {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(new BigDecimal(nonPositiveValue))
          .productId(VALID_PRODUCT_ID)
          .build();

      Set<ConstraintViolation<OfferRequest>> actualViolations = validator.validateProperty(
          offerRequest, "value");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("value must be bigger than 0");
    }

  }

  @Nested
  @DisplayName("Tests for productId validation")
  class ProductIdValidation {

    @ParameterizedTest
    @ValueSource(longs = {1L, 10000000000L})
    @DisplayName("Validate productId must not return violations when productId is valid")
    void validateProductId_MustNotReturnViolations_WhenProductIdIsValid(Long productId) {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(VALID_VALUE)
          .productId(productId)
          .build();

      Set<ConstraintViolation<OfferRequest>> actualViolations = validator.validateProperty(
          offerRequest, "productId");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @Test
    @DisplayName("Validate productId must return violations when productId is null")
    void validateProductId_MustReturnViolations_WhenProductIdIsNull() {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(VALID_VALUE)
          .productId(null)
          .build();

      Set<ConstraintViolation<OfferRequest>> actualViolations = validator.validateProperty(
          offerRequest, "productId");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("productId must not be null");
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, -100000L})
    @DisplayName("Validate ProductId must return violations when productId is non positive")
    void validateProductId_MustReturnViolations_WhenProductIdIsNonPositive(Long nonPositiveProductId) {
      OfferRequest offerRequest = OfferRequest.builder()
          .value(VALID_VALUE)
          .productId(nonPositiveProductId)
          .build();

      Set<ConstraintViolation<OfferRequest>> actualViolations = validator.validateProperty(
          offerRequest, "productId");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("productId must be bigger than 0");
    }

  }

}