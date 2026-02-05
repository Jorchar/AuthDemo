package org.example.authdemo.infrustracture.user.controller.dto;

import lombok.Builder;

import java.time.Instant;

@Builder
public record UserDto(
        String firstName,
        String lastName,
        String email,
        Instant createdAt,
        Instant updatedAt
) {
}
