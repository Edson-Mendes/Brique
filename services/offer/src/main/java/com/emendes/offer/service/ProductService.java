package com.emendes.offer.service;

import com.emendes.offer.dto.response.ProductResponse;

/**
 * Interface Service que contém as abstrações que manipulam o recurso Product
 * dentro do microservice Offer.
 */
public interface ProductService {

  /**
   * Busca por ID um Product disponível.
   *
   * @param productId ID do Product.
   * @return ProductResponse com as informações do Product.
   */
  ProductResponse findAvailableProductById(Long productId);

}
