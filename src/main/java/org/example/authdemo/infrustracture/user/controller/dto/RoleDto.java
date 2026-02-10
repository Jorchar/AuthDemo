package org.example.authdemo.infrustracture.user.controller.dto;

import lombok.Builder;

@Builder
public record RoleDto (
        String name
) {
}
