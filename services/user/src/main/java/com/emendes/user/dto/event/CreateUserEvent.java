package com.emendes.user.dto.event;

import lombok.Builder;

@Builder
public record CreateUserEvent(
    String username,
    String password,
    String authorities
) {
}
