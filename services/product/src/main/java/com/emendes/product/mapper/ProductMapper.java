package com.emendes.product.mapper;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.model.entity.Product;

public interface ProductMapper {

  ProductResponse toProductResponse(Product product);

  Product toProduct(ProductRequest productRequest);

}
