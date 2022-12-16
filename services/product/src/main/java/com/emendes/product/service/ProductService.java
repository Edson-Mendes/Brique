package com.emendes.product.service;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Page<ProductResponse> fetchAll(Pageable pageable);

  ProductResponse save(ProductRequest productRequest);

  ProductResponse find(Long id);

  void delete(Long id);

}
