package com.emendes.offer.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * Record DTO para receber dados de criação de Offer no corpo da requisição.
 * @param value do Product
 * @param productId do Product
 */
@Builder
public record OfferRequest(
    @NotNull(message = "value must not be null")
    @Digits(
        integer = 7, fraction = 2,
        message = "value must contains max {integer} integer digits and max {fraction} fraction digits, e.g. 290.98")
    @Positive(message = "value must be bigger than 0")
    BigDecimal value,
    @NotNull(message = "productId must not be null")
    @Positive(message = "productId must be bigger than 0")
    Long productId
) {

}
