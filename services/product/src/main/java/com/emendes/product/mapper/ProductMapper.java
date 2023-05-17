package com.emendes.product.mapper;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import com.emendes.product.model.entity.Product;

/**
 * Interface component que contém as abstrações de mapeamento de DTOs para a entidade Product e vice-versa.
 */
public interface ProductMapper {

  /**
   * Mapeia uma entidade Product para o DTO ProductResponse.
   * @param product que deve ser mapeado para ProductResponse
   * @return ProductResponse com as informações necessárias de Product.
   */
  ProductResponse toProductResponse(Product product);

  /**
   * Mapeia o DTO ProductRequest para a entidade Product.
   * @param productRequest que deve ser mapeado para Product
   * @return Product com dados vindo de productRequest.
   */
  Product toProduct(ProductRequest productRequest);

  /**
   * Insere os dados vindo do DTO ProductRequest em uma entidade Product
   * @param source que contém os novos dados de Product.
   * @param destination que receberá os novos dados vindo de ProductRequest
   */
  void merge(ProductRequest source, Product destination);

}
