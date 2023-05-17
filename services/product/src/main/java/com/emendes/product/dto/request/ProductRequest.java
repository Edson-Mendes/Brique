package com.emendes.product.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
    @NotBlank(message = "name must not be null or blank")
    @Size(max = 100, message = "name must be maximum {max} characters long")
    String name,
    @NotBlank(message = "description must not be null or blank")
    @Size(max = 255, message = "description must be maximum {max} characters long")
    String description,
    @NotNull(message = "price must not be null")
    @Digits(integer = 7, fraction = 2, message = "numerical value out of range ({integer} digits.{fraction} digits)")
    BigDecimal price
) {

}
