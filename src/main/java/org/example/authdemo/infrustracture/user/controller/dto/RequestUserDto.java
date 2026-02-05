package org.example.authdemo.infrustracture.user.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestUserDto(
        @NotBlank(message = "Firstname cannnot be blank")
        String firstName,
        @NotBlank(message = "Lastname cannnot be blank")
        String lastName,
        @NotBlank(message = "Email cannnot be blank")
        String email
) {
}
