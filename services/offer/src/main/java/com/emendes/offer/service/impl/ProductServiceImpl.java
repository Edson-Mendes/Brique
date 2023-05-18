package com.emendes.offer.service.impl;

import com.emendes.offer.client.ProductClient;
import com.emendes.offer.dto.response.ProductResponse;
import com.emendes.offer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementação de {@link com.emendes.offer.service.ProductService}.
 */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductClient productClient;

  @Override
  public ProductResponse findAvailableProductById(Long productId) {
    // TODO: Adicionar verificação se o produto está mesmo disponível.
    return productClient.findProductById(productId);
  }

}
