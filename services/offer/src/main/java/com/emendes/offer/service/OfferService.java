package com.emendes.offer.service;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;

/**
 * Interface Service que contém as abstrações que manipulam o recurso Offer.
 */
public interface OfferService {

  OfferResponse makeOffer(OfferRequest offerRequest);

}
