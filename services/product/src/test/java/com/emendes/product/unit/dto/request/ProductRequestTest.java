package com.emendes.product.unit.dto.request;

import com.emendes.product.dto.request.ProductRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@DisplayName("Unit tests for ProductRequest")
class ProductRequestTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
  private final String VALID_NAME = "Bugiganga lorem ipsum";
  private final String VALID_DESCRIPTION = "Description Lorem Ipsum";
  private final BigDecimal VALID_PRICE = new BigDecimal("200.00");

  @Nested
  @DisplayName("Tests for name validation")
  class NameValidation {

    @ParameterizedTest
    @ValueSource(strings = {"P", "Product name"})
    @DisplayName("Validate name must not return violations when name is valid")
    void validateName_MustNotReturnViolations_WhenNameIsValid(String name) {
      ProductRequest productRequest = ProductRequest.builder()
          .name(name)
          .description(VALID_DESCRIPTION)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "name");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @Test
    @DisplayName("Validate name must return violations when name is null")
    void validateName_MustReturnViolations_WhenNameIsNull() {
      ProductRequest productRequest = ProductRequest.builder()
          .name(null)
          .description(VALID_DESCRIPTION)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "name");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("name must not be null or blank");
    }

    @Test
    @DisplayName("Validate name must return violations when name is empty")
    void validateName_MustReturnViolations_WhenNameIsEmpty() {
      ProductRequest productRequest = ProductRequest.builder()
          .name("")
          .description(VALID_DESCRIPTION)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "name");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("name must not be null or blank");
    }

    @Test
    @DisplayName("Validate name must return violations when name is blank")
    void validateName_MustReturnViolations_WhenNameIsBlank() {
      ProductRequest productRequest = ProductRequest.builder()
          .name("   ")
          .description(VALID_DESCRIPTION)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "name");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("name must not be null or blank");
    }

    @Test
    @DisplayName("Validate name must return violations when name size is bigger than 100 characters")
    void validateName_MustReturnViolations_WhenNameSizeIsBiggerThan100Characters() {
      String nameWith101Characters = "name xpto!name xpto!name xpto!name xpto!name xpto!" +
          "name xpto!name xpto!name xpto!name xpto!name xpto!.";
      ProductRequest productRequest = ProductRequest.builder()
          .name(nameWith101Characters)
          .description(VALID_DESCRIPTION)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "name");

      Assertions.assertThat(nameWith101Characters).hasSize(101);
      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("name must be maximum 100 characters long");
    }

  }

  @Nested
  @DisplayName("Tests for description validation")
  class DescriptionValidation {

    @ParameterizedTest
    @ValueSource(strings = {"Description Lorem Ipsum", "D"})
    @DisplayName("Validate description must not return violations when description is valid")
    void validateDescription_MustNotReturnViolations_WhenDescriptionIsValid(String description) {
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description(description)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "description");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @Test
    @DisplayName("Validate description must return violations when description is null")
    void validateDescription_MustReturnViolations_WhenDescriptionIsNull() {
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description(null)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "description");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("description must not be null or blank");
    }

    @Test
    @DisplayName("Validate description must return violations when description is empty")
    void validateDescription_MustReturnViolations_WhenDescriptionIsEmpty() {
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description("")
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "description");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("description must not be null or blank");
    }

    @Test
    @DisplayName("Validate description must return violations when description is blank")
    void validateDescription_MustReturnViolations_WhenDescriptionIsBlank() {
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description("   ")
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "description");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("description must not be null or blank");
    }

    @Test
    @DisplayName("Validate description must return violations when description size is bigger than 255 characters")
    void validateDescription_MustReturnViolations_WhenDescriptionSizeIsBiggerThan255Characters() {
      String descriptionWith256Characters = "descriptionxptodescriptionxptodescriptionxptodescriptionxpto"+
          "descriptionxptodescriptionxptodescriptionxptodescriptionxptodescriptionxptodescriptionxpto"+
          "descriptionxptodescriptionxptodescriptionxptodescriptionxptodescriptionxptodescriptionxptodescriptionxpto.";
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description(descriptionWith256Characters)
          .price(VALID_PRICE)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "description");

      Assertions.assertThat(descriptionWith256Characters).hasSize(256);
      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("description must be maximum 255 characters long");
    }

  }

  @Nested
  @DisplayName("Tests for price validation")
  class PriceValidation {

    @ParameterizedTest
    @ValueSource(strings = {"9999999.99", "0.00", "1.00", "100.1", "100"})
    @DisplayName("Validate price must not return violations when price is valid")
    void validatePrice_MustNotReturnViolations_WhenPriceIsValid(String price) {
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description(VALID_DESCRIPTION)
          .price(new BigDecimal(price))
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "price");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @Test
    @DisplayName("Validate price must return violations when price is null")
    void validatePrice_MustReturnViolations_WhenPriceIsNull() {
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description(VALID_DESCRIPTION)
          .price(null)
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "price");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("price must not be null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"99999999.99", "0.001"})
    @DisplayName("Validate price must return violations when the number of digits is invalid")
    void validatePrice_MustReturnViolations_WhenTheNumberOfDigitsIsInvalid(String invalidPrice) {
      ProductRequest productRequest = ProductRequest.builder()
          .name(VALID_NAME)
          .description(VALID_DESCRIPTION)
          .price(new BigDecimal(invalidPrice))
          .build();

      Set<ConstraintViolation<ProductRequest>> actualViolations = validator.validateProperty(
          productRequest, "price");

      Assertions.assertThat(actualViolations).isNotEmpty().hasSize(1);
      Assertions.assertThat(actualViolations.stream().findFirst().get().getMessage())
          .isEqualTo("price must contains max 7 integer digits and max 2 fraction digits, e.g. 290.98");
    }

  }

}