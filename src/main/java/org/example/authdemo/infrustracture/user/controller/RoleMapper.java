package org.example.authdemo.infrustracture.user.controller;

import jakarta.validation.Valid;
import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.infrustracture.user.controller.dto.RoleDto;

public final class RoleMapper {
    public static Role mapToDomainRole(@Valid RoleDto role) {
        return Role.builder()
                .name(role.name())
                .build();
    }

    public static RoleDto mapToRoleDto(Role role) {
        return RoleDto.builder()
                .name(role.getName())
                .build();
    }
}
