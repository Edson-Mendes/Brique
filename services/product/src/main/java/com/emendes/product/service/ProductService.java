package com.emendes.product.service;

import com.emendes.product.dto.request.ProductRequest;
import com.emendes.product.dto.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface Service que contém as abstrações que manipulam o recurso Product.
 */
public interface ProductService {

  /**
   * Busca paginada de Product.
   * @param pageable Objeto que contém page e size.
   * @return Page of ProductResponse
   */
  Page<ProductResponse> fetchAll(Pageable pageable);

  /**
   * Salva um Product no banco dados.
   *
   * @param productRequest que contém os dados do Product que será salvo.
   * @return ProductResponse que contém os dados do Product salvo.
   */
  ProductResponse save(ProductRequest productRequest);

  /**
   * Busca um Product por id.
   * @param id do Product a ser buscado.
   * @return ProductResponse contendo os dados do Product.
   */
  ProductResponse find(Long id);

  /**
   * Atualiza um Product.
   * @param id do Product a ser atualizado.
   * @param productRequest contendo as novas informações do Product.
   * @return ProductResponse com as novas informações do Product.
   */
  ProductResponse update(Long id, ProductRequest productRequest);

  /**
   * Deleta um Product.
   * @param id do Product a ser deletado.
   */
  void delete(Long id);

}
