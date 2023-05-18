package com.emendes.product.service.impl;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.exception.ProductNotFoundException;
import com.emendes.product.mapper.ProductMapper;
import com.emendes.product.model.entity.Product;
import com.emendes.product.repository.ProductRepository;
import com.emendes.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Implementação de {@link ProductService}
 */
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

  @Override
  public ProductResponse save(ProductRequest productRequest) {
    Product product = mapper.toProduct(productRequest);
    product.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

    productRepository.save(product);
    log.info("product with id {} was saved", product.getId());

    return mapper.toProductResponse(product);
  }

  @Override
  public ProductResponse find(Long id) {
    log.info("Searching for product with id : {}", id);
    return mapper.toProductResponse(findById(id));
  }

  @Override
  public ProductResponse update(Long id, ProductRequest productRequest) {
    Product productToBeUpdated = findById(id);

    mapper.merge(productRequest, productToBeUpdated);
    productRepository.save(productToBeUpdated);

    log.info("Product with id {} was updated", productToBeUpdated.getId());

    return mapper.toProductResponse(productToBeUpdated);
  }

  @Override
  public void delete(Long id) {
    Product product = findById(id);
    productRepository.delete(product);
    log.info("product with id {} was deleted", product.getId());
  }

  private Product findById(Long id) {
    return productRepository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException("Product not found for id " + id));
  }

}
