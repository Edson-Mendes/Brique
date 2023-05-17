package com.emendes.product.mapper.impl;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.mapper.ProductMapper;
import com.emendes.product.model.entity.Product;
import org.springframework.stereotype.Component;

/**
 * Implementação de ProductMapper.
 */
@Component
public class ProductMapperImpl implements ProductMapper {

  @Override
  public ProductResponse toProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
  }

  @Override
  public Product toProduct(ProductRequest productRequest) {
    return Product.builder()
        .name(productRequest.name())
        .description(productRequest.description())
        .price(productRequest.price())
        .build();
  }

  @Override
  public void merge(ProductRequest source, Product destination) {
    destination.setName(source.name());
    destination.setDescription(source.description());
    destination.setPrice(source.price());
  }

}
