package com.emendes.offer.service;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Interface Service que contém as abstrações que manipulam o recurso Offer.
 */
public interface OfferService {

  /**
   * Regras de negócio para fazer uma Offer.
   * @param offerRequest contendo os dados da Offer.
   * @return OfferResponse contendo os dados da Offer criada.
   */
  OfferResponse makeOffer(OfferRequest offerRequest);

  /**
   * Busca paginada de Offers.
   * @param pageable objeto que contém os parâmetros de paginação.
   * @return Page of OfferResponse.
   */
  Page<OfferResponse> fetchAll(Pageable pageable);

}
