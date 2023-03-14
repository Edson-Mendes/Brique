package com.emendes.offer.service.impl;

import com.emendes.offer.client.ProductClient;
import com.emendes.offer.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductClient productClient;

  @Override
  public boolean isAvailable(Long id) {
    // TODO: Tratar caso o product-service esteja indisponível
    // TODO: Adicionar verificação se o produto está disponivel.
    return productClient.findProduct(id) == null;
  }

}
