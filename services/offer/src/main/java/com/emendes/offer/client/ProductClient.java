package com.emendes.offer.client;

import com.emendes.offer.dto.response.ProductResponse;

/**
 * Interface Client que contém as abstrações para interagir com o Microservice Product.
 */
public interface ProductClient {

  /**
   * Busca um Product por ID.
   * @param id do Product a ser buscado.
   * @return ProductResponse contendo as informações do Product.
   */
  ProductResponse findProductById(Long id);

}
