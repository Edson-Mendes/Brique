package com.emendes.offer.service;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;

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

}
