package com.emendes.product.unit.service;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.exception.ProductNotFoundException;
import com.emendes.product.mapper.ProductMapper;
import com.emendes.product.model.entity.Product;
import com.emendes.product.repository.ProductRepository;
import com.emendes.product.service.impl.ProductServiceImpl;
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
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DisplayName("Unit tests for ProductServiceImpl")
class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl productService;
  @Mock
  private ProductRepository productRepositoryMock;
  @Mock
  private ProductMapper mapperMock;


  @Nested
  @DisplayName("Unit tests for save method")
  class SaveMethod {

    @Test
    @DisplayName("save must return ProductResponse when save successfully")
    void save_MustReturnProductResponse_WhenSaveSuccessfully() {
      Product productWithoutIdCreatedAt = Product.builder()
          .name("Arranhador Lorem Ipsum")
          .description("Descrição Dolor Sit")
          .price(new BigDecimal("200.00"))
          .build();
      BDDMockito.when(mapperMock.toProduct(ArgumentMatchers.any())).thenReturn(productWithoutIdCreatedAt);
      BDDMockito.when(productRepositoryMock.save(ArgumentMatchers.any())).thenReturn(product());
      BDDMockito.when(mapperMock.toProductResponse(ArgumentMatchers.any())).thenReturn(productResponse());

      ProductRequest productRequest = ProductRequest.builder()
          .name("Arranhador Lorem Ipsum")
          .description("Descrição Dolor Sit")
          .price(new BigDecimal("200.00"))
          .build();

      ProductResponse actualProductResponse = productService.save(productRequest);
      BDDMockito.verify(mapperMock).toProduct(ArgumentMatchers.any());
      BDDMockito.verify(productRepositoryMock).save(ArgumentMatchers.any());
      BDDMockito.verify(mapperMock).toProductResponse(ArgumentMatchers.any());

      ProductResponse expectedProductResponse = ProductResponse.builder()
          .id(1000L)
          .name("Arranhador Lorem Ipsum")
          .description("Descrição Dolor Sit")
          .price(new BigDecimal("200.00"))
          .build();

      Assertions.assertThat(actualProductResponse).isNotNull().isEqualTo(expectedProductResponse);
    }

  }

  @Nested
  @DisplayName("Unit tests for find method")
  class FindMethod {

    @Test
    @DisplayName("find must return ProductResponse when found successfully")
    void find_MustReturnProductResponse_WhenFoundSuccessfully() {
      BDDMockito.when(productRepositoryMock.findById(1000L)).thenReturn(optionalProduct());
      BDDMockito.when(mapperMock.toProductResponse(ArgumentMatchers.any()))
          .thenReturn(productResponse());

      ProductResponse actualProductResponse = productService.find(1000L);
      BDDMockito.verify(productRepositoryMock).findById(1000L);

      ProductResponse expectedProductResponse = ProductResponse.builder()
          .id(1000L)
          .name("Arranhador Lorem Ipsum")
          .description("Descrição Dolor Sit")
          .price(new BigDecimal("200.00"))
          .build();

      Assertions.assertThat(actualProductResponse).isNotNull().isEqualTo(expectedProductResponse);
    }

    @Test
    @DisplayName("find must throw ProductNotFoundException when product does not exist")
    void find_MustThrowProductNotFoundException_WhenProductDoesNotExist() {
//      Não é necessário mockar o comportamento do productRepository.findById pois o valor default é empty optional.
      Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
          .isThrownBy(() -> productService.find(9999L))
          .withMessage("Product not found for id 9999");
    }

  }

  @Nested
  @DisplayName("Unit tests for update method")
  class UpdateMethod {

    @Test
    @DisplayName("update must return ProductResponse when update successfully")
    void update_MustReturnProductResponse_WhenUpdateSuccessfully() {
      BDDMockito.when(productRepositoryMock.findById(1000L)).thenReturn(optionalProduct());
      BDDMockito.when(mapperMock.toProductResponse(ArgumentMatchers.any()))
          .thenReturn(updateProductResponse());

      ProductRequest productRequest = ProductRequest.builder()
          .name("Arranhador Lorem Ipsum updated")
          .description("Descrição Dolor Sit updated")
          .price(new BigDecimal("250.00"))
          .build();

      ProductResponse actualProductResponse = productService.update(1000L, productRequest);
      BDDMockito.verify(mapperMock).merge(ArgumentMatchers.any(), ArgumentMatchers.any());
      BDDMockito.verify(productRepositoryMock).save(ArgumentMatchers.any());

      ProductResponse expectedProductResponse = ProductResponse.builder()
          .id(1000L)
          .name("Arranhador Lorem Ipsum updated")
          .description("Descrição Dolor Sit updated")
          .price(new BigDecimal("250.00"))
          .build();

      Assertions.assertThat(actualProductResponse).isNotNull().isEqualTo(expectedProductResponse);
    }

    @Test
    @DisplayName("update must throw ProductNotFoundException when product does not exist")
    void update_MustThrowProductNotFoundException_WhenProductDoesNotExist() {
      ProductRequest productRequest = ProductRequest.builder()
          .name("Arranhador Lorem Ipsum updated")
          .description("Descrição Dolor Sit updated")
          .price(new BigDecimal("250.00"))
          .build();

//      Não é necessário mockar o comportamento do productRepository.findById pois o valor default é empty optional.
      Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
          .isThrownBy(() -> productService.update(9999L, productRequest))
          .withMessage("Product not found for id 9999");
    }

  }

  @Nested
  @DisplayName("Unit tests for delete method")
  class DeleteMethod {

    @Test
    @DisplayName("delete must call productRepository.delete when delete successfully")
    void delete_MustCallProductRepositoryDelete_WhenUpdateSuccessfully() {
      BDDMockito.when(productRepositoryMock.findById(1000L)).thenReturn(optionalProduct());

      productService.delete(1000L);
      BDDMockito.verify(productRepositoryMock).delete(ArgumentMatchers.any());

    }

    @Test
    @DisplayName("delete must throw ProductNotFoundException when product does not exist")
    void delete_MustThrowProductNotFoundException_WhenProductDoesNotExist() {
//      Não é necessário mockar o comportamento do productRepository.findById pois o valor default é empty optional.
      Assertions.assertThatExceptionOfType(ProductNotFoundException.class)
          .isThrownBy(() -> productService.delete(9999L))
          .withMessage("Product not found for id 9999");
    }

  }

  private Product product() {
    return  Product.builder()
        .id(1000L)
        .name("Arranhador Lorem Ipsum")
        .description("Descrição Dolor Sit")
        .price(new BigDecimal("200.00"))
        .createdAt(LocalDateTime.parse("2022-12-19T10:00:00"))
        .build();
  }

  private ProductResponse productResponse() {
    return ProductResponse.builder()
        .id(1000L)
        .name("Arranhador Lorem Ipsum")
        .description("Descrição Dolor Sit")
        .price(new BigDecimal("200.00"))
        .build();
  }

  private ProductResponse updateProductResponse() {
    return ProductResponse.builder()
        .id(1000L)
        .name("Arranhador Lorem Ipsum updated")
        .description("Descrição Dolor Sit updated")
        .price(new BigDecimal("250.00"))
        .build();
  }

  private Optional<Product> optionalProduct() {
    return Optional.of(product());
  }

}