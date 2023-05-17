package com.emendes.product.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

/**
 * Record DTO para enviar informações do Product para o cliente no corpo da resposta.
 * @param id do Product
 * @param name do Product
 * @param description do Product
 * @param price do Product
 */
@Builder
public record ProductResponse(
    Long id,
    String name,
    String description,
    BigDecimal price
) {
}
