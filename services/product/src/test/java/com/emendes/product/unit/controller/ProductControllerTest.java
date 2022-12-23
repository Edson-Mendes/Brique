package com.emendes.product.unit.controller;

import com.emendes.product.controller.ProductController;
import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.exception.ProductNotFoundException;
import com.emendes.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Slf4j
@WebMvcTest(ProductController.class)
@DisplayName("Unit tests for ProductController")
class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;

  private final String PRODUCT_BASE_URI = "/api/products";

  @MockBean
  private ProductService productServiceMock;
  private final Pageable DEFAULT_PAGEABLE = PageRequest.of(0, 10);

  @Nested
  @DisplayName("Unit tests for fetchAll endpoint")
  class fetchAllEndpoint {

    @Test
    @DisplayName("fetchAll must return 200 and Page<ProductResponse> when fetch successfully")
    void fetchAll_MustReturn200AndPageProductResponse_WhenFetchSuccessfully() throws Exception {
      BDDMockito.when(productServiceMock.fetchAll(DEFAULT_PAGEABLE)).thenReturn(pageProductResponse());

      mockMvc.perform(get(PRODUCT_BASE_URI))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.totalElements").value(1))
          .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1000L))
          .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name")
              .value("Arranhador Lorem Ipsum"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].description")
              .value("Descrição Dolor Sit"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].price").value(200.00));
    }

  }

  @Nested
  @DisplayName("Unit tests for save endpoint")
  class SaveEndpoint {

    @Test
    @DisplayName("save must return 201 and ProductResponse when save successfully")
    void save_MustReturn201AndProductResponse_WhenSaveSuccessfully() throws Exception {
      BDDMockito.when(productServiceMock.save(ArgumentMatchers.any(ProductRequest.class)))
          .thenReturn(productResponse());

      String requestBody = """
          {
            "name" : "Arranhador Lorem Ipsum",
            "description" : "Descrição Dolor Sit",
            "price" : 200.00
          }
          """;
      mockMvc.perform(post(PRODUCT_BASE_URI).contentType(MediaType.APPLICATION_JSON).content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isCreated())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1000L))
          .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arranhador Lorem Ipsum"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Descrição Dolor Sit"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(200.00));
    }

    @Test
    @DisplayName("save must return 400 and ValidationErrorDetails when request body is invalid")
    void save_MustReturn400AndValidationErrorDetails_WhenRequestBodyIsInvalid() throws Exception {
      String requestBody = """
          {
            "name" : "Arranhador Lorem Ipsum",
            "description" : "",
            "price" : 200.00
          }
          """;
      mockMvc.perform(post(PRODUCT_BASE_URI).contentType(MediaType.APPLICATION_JSON).content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid field(s)"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.fields").value("description"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors")
              .value("description must not be null or blank"));
    }

  }

  @Nested
  @DisplayName("Unit tests for findById endpoint")
  class FindByIdEndpoint {

    @Test
    @DisplayName("findById must return 200 and ProductResponse when found successfully")
    void findById_MustReturn200AndProductResponse_WhenFoundSuccessfully() throws Exception {
      BDDMockito.when(productServiceMock.find(1000L))
          .thenReturn(productResponse());

      mockMvc.perform(get(PRODUCT_BASE_URI + "/1000"))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1000L))
          .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arranhador Lorem Ipsum"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Descrição Dolor Sit"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(200.00));
    }

    @Test
    @DisplayName("findById must return 400 and ErrorDetails when path variable is invalid")
    void findById_MustReturn400AndErrorDetails_WhenPathVariableIsInvalid() throws Exception {
      mockMvc.perform(get(PRODUCT_BASE_URI + "/100o"))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Type Mismatch"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products/100o"));
    }

    @Test
    @DisplayName("findById must return 404 and ErrorDetails when not found product")
    void findById_MustReturn404AndErrorDetails_WhenNotFoundProduct() throws Exception {
      BDDMockito.given(productServiceMock.find(9999L))
          .willThrow(new ProductNotFoundException("Product not found for id 9999"));

      mockMvc.perform(get(PRODUCT_BASE_URI + "/9999"))
          .andExpect(MockMvcResultMatchers.status().isNotFound())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Resource not found"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.message")
              .value("Product not found for id 9999"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products/9999"));
    }

  }

  @Nested
  @DisplayName("Unit tests for update endpoint")
  class UpdateEndpoint {

    @Test
    @DisplayName("update must return 200 and ProductResponse when update successfully")
    void update_MustReturn200AndProductResponse_WhenUpdateSuccessfully() throws Exception {
      BDDMockito.when(productServiceMock.update(
              ArgumentMatchers.eq(1000L), ArgumentMatchers.any(ProductRequest.class)))
          .thenReturn(updateProductResponse());

      String requestBody = """
          {
            "name" : "Arranhador Lorem Ipsum updated",
            "description" : "Descrição Dolor Sit updated",
            "price" : 250.00
          }
          """;
      mockMvc.perform(put(PRODUCT_BASE_URI + "/1000").contentType(MediaType.APPLICATION_JSON).content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1000L))
          .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Arranhador Lorem Ipsum updated"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.description")
              .value("Descrição Dolor Sit updated"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(250.00));
    }

    @Test
    @DisplayName("update must return 400 and ErrorDetails when path variable is invalid")
    void update_MustReturn400AndErrorDetails_WhenPathVariableIsInvalid() throws Exception {
      String requestBody = """
          {
            "name" : "Arranhador Lorem Ipsum updated",
            "description" : "Descrição Dolor Sit updated",
            "price" : 250.00
          }
          """;

      mockMvc.perform(put(PRODUCT_BASE_URI + "/100o").contentType(MediaType.APPLICATION_JSON).content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Type Mismatch"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products/100o"));
    }

    @Test
    @DisplayName("update must return 400 and ValidationErrorDetails when request body is invalid")
    void update_MustReturn400AndValidationErrorDetails_WhenRequestBodyIsInvalid() throws Exception {
      String requestBody = """
          {
            "name" : "Arranhador Lorem Ipsum updated",
            "description" : "",
            "price" : 250.00
          }
          """;

      mockMvc.perform(put(PRODUCT_BASE_URI + "/1000").contentType(MediaType.APPLICATION_JSON).content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Invalid field(s)"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products/1000"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.fields").value("description"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors")
              .value("description must not be null or blank"));
    }

    @Test
    @DisplayName("update must return 404 and ErrorDetails when not found product")
    void update_MustReturn404AndErrorDetails_WhenNotFoundProduct() throws Exception {
      BDDMockito.given(productServiceMock.update(
              ArgumentMatchers.eq(9999L), ArgumentMatchers.any(ProductRequest.class)))
          .willThrow(new ProductNotFoundException("Product not found for id 9999"));

      String requestBody = """
          {
            "name" : "Arranhador Lorem Ipsum updated",
            "description" : "Descrição Dolor Sit updated",
            "price" : 250.00
          }
          """;

      mockMvc.perform(put(PRODUCT_BASE_URI + "/9999").contentType(MediaType.APPLICATION_JSON).content(requestBody))
          .andExpect(MockMvcResultMatchers.status().isNotFound())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Resource not found"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.message")
              .value("Product not found for id 9999"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products/9999"));
    }

  }

  @Nested
  @DisplayName("Unit tests for delete endpoint")
  class DeleteEndpoint {

    @Test
    @DisplayName("delete must return 204 when delete successfully")
    void delete_MustReturn204_WhenDeleteSuccessfully() throws Exception {
      mockMvc.perform(delete(PRODUCT_BASE_URI + "/1000"))
          .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("delete must return 400 and ErrorDetails when path variable is invalid")
    void delete_MustReturn400AndErrorDetails_WhenPathVariableIsInvalid() throws Exception {
      mockMvc.perform(delete(PRODUCT_BASE_URI + "/100o"))
          .andExpect(MockMvcResultMatchers.status().isBadRequest())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Type Mismatch"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products/100o"));
    }

    @Test
    @DisplayName("delete must return 404 and ErrorDetails when not found product")
    void delete_MustReturn404AndErrorDetails_WhenNotFoundProduct() throws Exception {
      BDDMockito.doThrow(new ProductNotFoundException("Product not found for id 9999"))
          .when(productServiceMock).delete(9999L);

      mockMvc.perform(delete(PRODUCT_BASE_URI + "/9999"))
          .andExpect(MockMvcResultMatchers.status().isNotFound())
          .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Resource not found"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.message")
              .value("Product not found for id 9999"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
          .andExpect(MockMvcResultMatchers.jsonPath("$.path").value("/api/products/9999"));
    }

  }

  private ProductResponse productResponse() {
    return ProductResponse.builder()
        .id(1000L)
        .name("Arranhador Lorem Ipsum")
        .description("Descrição Dolor Sit")
        .price(new BigDecimal("200.00"))
        .build();
  }

  private Page<ProductResponse> pageProductResponse() {
    List<ProductResponse> listProduct = List.of(productResponse());
    return new PageImpl<>(listProduct, DEFAULT_PAGEABLE, 1);
  }

  private ProductResponse updateProductResponse() {
    return ProductResponse.builder()
        .id(1000L)
        .name("Arranhador Lorem Ipsum updated")
        .description("Descrição Dolor Sit updated")
        .price(new BigDecimal("250.00"))
        .build();
  }

}