package com.emendes.product.mapper.impl;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.mapper.ProductMapper;
import com.emendes.product.model.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductMapperImpl implements ProductMapper {

  private final ModelMapper mapper;

  @Override
  public ProductResponse toProductResponse(Product product) {
    return mapper.map(product, ProductResponse.class);
  }

  @Override
  public Product toProduct(ProductRequest productRequest) {
    return mapper.map(productRequest, Product.class);
  }

}
