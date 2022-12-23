package com.emendes.product.unit.controller;

import com.emendes.product.controller.ProductController;
import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
          .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Arranhador Lorem Ipsum"))
          .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].description").value("Descrição Dolor Sit"))
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
          .andExpect(MockMvcResultMatchers.jsonPath("$.errors").value("description must not be null or blank"));
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

}