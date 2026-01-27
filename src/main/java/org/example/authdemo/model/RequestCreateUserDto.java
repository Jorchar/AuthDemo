package org.example.authdemo.model;

import jakarta.validation.constraints.NotBlank;

public record RequestCreateUserDto(
        @NotBlank
        String firstname,
        @NotBlank
        String lastname
) {

}
