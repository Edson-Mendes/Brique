package com.emendes.offer.dto.response;

import lombok.*;

import java.math.BigDecimal;

/**
 * Record DTO para enviar informações do Offer para o cliente no corpo da resposta.
 *
 * @param id        do Product
 * @param value     do Product
 * @param status    do Product
 * @param productId do Product
 */
@Builder
public record OfferResponse(
    Long id,
    BigDecimal value,
    String status,
    ProductResponse product
) {

}
