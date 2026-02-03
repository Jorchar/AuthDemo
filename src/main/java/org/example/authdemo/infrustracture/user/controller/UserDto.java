package org.example.authdemo.infrustracture.user.controller;

import lombok.Builder;

@Builder
public record UserDto(
        String firstName,
        String lastName
) {
}
