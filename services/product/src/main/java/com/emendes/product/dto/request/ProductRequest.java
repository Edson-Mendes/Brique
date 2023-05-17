package com.emendes.product.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * Record DTO para receber dados de criação/atualização de Product no corpo da requisição.
 * @param name do Product
 * @param description do Product
 * @param price do Product
 */
@Builder
public record ProductRequest(
    @NotBlank(message = "name must not be null or blank")
    @Size(max = 100, message = "name must be maximum {max} characters long")
    String name,
    @NotBlank(message = "description must not be null or blank")
    @Size(max = 255, message = "description must be maximum {max} characters long")
    String description,
    @NotNull(message = "price must not be null")
    @Digits(
        integer = 7, fraction = 2,
        message = "price must contains max {integer} integer digits and max {fraction} fraction digits, e.g. 290.98")
    BigDecimal price
) {

}
