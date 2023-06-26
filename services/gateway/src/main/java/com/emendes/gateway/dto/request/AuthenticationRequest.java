package com.emendes.gateway.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

/**
 * Record DTO para receber dados de autenticação do usuário no corpo da requisição.
 *
 * @param email    do usuário
 * @param password do usuário
 */
@Builder
public record AuthenticationRequest(
    @NotBlank(message = "email must not be blank")
    @Email(message = "must be a well formed email")
    String email,
    @NotBlank(message = "password must not be blank")
    String password
) {
}
