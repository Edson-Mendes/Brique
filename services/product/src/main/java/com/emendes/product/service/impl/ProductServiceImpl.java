package com.emendes.product.service.impl;

import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.mapper.ProductMapper;
import com.emendes.product.model.entity.Product;
import com.emendes.product.repository.ProductRepository;
import com.emendes.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper mapper;

  @Override
  public Page<ProductResponse> fetchAll(Pageable pageable) {
    Page<Product> productsPage = productRepository.findAll(pageable);
    log.info("fetching page of products");

    return productsPage.map(mapper::toProductResponse);
  }

}
