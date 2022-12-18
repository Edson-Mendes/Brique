package com.emendes.product.unit.mapper;

import com.emendes.product.config.bean.ModelMapperConfig;
import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.mapper.ProductMapper;
import com.emendes.product.mapper.impl.ProductMapperImpl;
import com.emendes.product.model.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DisplayName("Unit tests for ProductMapperImpl")
class ProductMapperImplTest {

  private ProductMapper productMapper;

  @BeforeEach
  void setUp() {
    productMapper = new ProductMapperImpl(new ModelMapperConfig().modelMapper());
  }

  @Test
  @DisplayName("toProductResponse must return ProductResponse when map successfully")
  void toProductResponse_MustReturnProductResponse_WhenMapSuccessfully() {
    Product productToBeMapped = Product.builder()
        .id(100L)
        .name("Arranhador Lorem Ipsum")
        .description("description lorem ipsum")
        .price(new BigDecimal("250.00"))
        .createdAt(LocalDateTime.parse("2022-12-10T10:00:00"))
      .build();

    ProductResponse actualProductResponse = productMapper.toProductResponse(productToBeMapped);
    ProductResponse expectedProductResponse = ProductResponse.builder()
        .id(100L)
        .name("Arranhador Lorem Ipsum")
        .description("description lorem ipsum")
        .price(new BigDecimal("250.00"))
        .build();

    Assertions.assertThat(actualProductResponse)
        .isNotNull()
        .isEqualTo(expectedProductResponse);
  }

  @Test
  @DisplayName("toProduct must return Product when map successfully")
  void toProduct_MustReturnProduct_WhenMapSuccessfully() {
    ProductRequest productRequestToBeMapped = ProductRequest.builder()
        .name("Arranhador Lorem Ipsum")
        .description("description lorem ipsum")
        .price(new BigDecimal("250.00"))
        .build();

    Product actualProduct = productMapper.toProduct(productRequestToBeMapped);

    Assertions.assertThat(actualProduct).isNotNull();
    Assertions.assertThat(actualProduct.getId()).isNull();
    Assertions.assertThat(actualProduct.getCreatedAt()).isNull();
    Assertions.assertThat(actualProduct.getName()).isNotNull().isEqualTo("Arranhador Lorem Ipsum");
    Assertions.assertThat(actualProduct.getDescription()).isNotNull().isEqualTo("description lorem ipsum");
    Assertions.assertThat(actualProduct.getPrice()).isNotNull().isEqualTo("250.00");
  }

  @Test
  @DisplayName("merge must merge ProductRequest into Product when merge successfully")
  void merge_MustMergeProductRequestIntoProduct_WhenMergeSuccessfully() {
    ProductRequest productRequestSource = ProductRequest.builder()
        .name("Arranhador Lorem Ipsum updated")
        .description("description lorem ipsum updated")
        .price(new BigDecimal("250.00"))
        .build();

    Product productDestination = Product.builder()
        .id(100L)
        .name("Arranhador Lorem Ipsum")
        .description("description lorem ipsum")
        .price(new BigDecimal("250.00"))
        .createdAt(LocalDateTime.parse("2022-12-10T10:00:00"))
        .build();

    productMapper.merge(productRequestSource, productDestination);

    Assertions.assertThat(productDestination).isNotNull();
    Assertions.assertThat(productDestination.getId()).isEqualTo(100L);
    Assertions.assertThat(productDestination.getName()).isEqualTo("Arranhador Lorem Ipsum updated");
    Assertions.assertThat(productDestination.getDescription()).isEqualTo("description lorem ipsum updated");
    Assertions.assertThat(productDestination.getPrice()).isEqualTo(new BigDecimal("250.00"));
    Assertions.assertThat(productDestination.getCreatedAt()).isEqualTo("2022-12-10T10:00:00");
  }

}