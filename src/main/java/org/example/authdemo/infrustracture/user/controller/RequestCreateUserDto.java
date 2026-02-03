package org.example.authdemo.infrustracture.user.controller;

import jakarta.validation.constraints.NotBlank;

public record RequestCreateUserDto(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {

}
