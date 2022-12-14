package com.emendes.product.service.impl;

import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

  @Override
  public Page<ProductResponse> fetchAll(Pageable pageable) {
    ProductResponse productResponse = ProductResponse.builder()
        .name("Arranhador para gatos")
        .description("Arranhador em ótimo estado, pouco uso.")
        .build();

    return new PageImpl<>(List.of(productResponse), pageable, 1);
  }

}
