package com.emendes.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Record DTO para receber dados de criação do User no corpo da requisição.
 * @param name do User
 * @param email do User
 * @param password do User
 */
@Builder
public record CreateUserRequest(
    @NotBlank(message = "name must not be blank")
    String name,
    @NotBlank(message = "email must not be blank")
    String email,
    @NotBlank(message = "password must not be blank")
    @Size(min = 8, max = 30, message = "password must be between {min} and {max} characters long")
    String password
) {
}
