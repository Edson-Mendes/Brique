package com.emendes.offer.mapper;

import com.emendes.offer.dto.request.OfferRequest;
import com.emendes.offer.dto.response.OfferResponse;
import com.emendes.offer.dto.response.ProductResponse;
import com.emendes.offer.model.entity.Offer;

/**
 * Interface component que contém as abstrações de mapeamento de DTOs para a entidade Offer e vice-versa.
 */
public interface OfferMapper {

  /**
   * Mapeia o DTO OfferRequest para a entidade Offer.
   *
   * @param offerRequest que deve ser mapeado para Offer
   * @return Offer com dados vindo de offerRequest.
   */
  Offer toOffer(OfferRequest offerRequest);

  /**
   * Mapeia uma entidade Offer para o DTO OfferResponse.
   *
   * @param offer que deve ser mapeado para OfferResponse
   * @return OfferResponse com as informações necessárias de Offer.
   */
  OfferResponse toOfferResponse(Offer offer, ProductResponse productResponse);

}
