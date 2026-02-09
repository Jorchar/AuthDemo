package org.example.authdemo.infrustracture.user.persistance.role;

import org.example.authdemo.domain.user.model.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public final class RoleJpaMapper {
    public static Role mapToDomainRole(RoleJpa role) {
        return Role.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static RoleJpa mapToJpaRole(Role role) {
        return new RoleJpa(
                role.getName()
        );
    }

    public static Set<Role> mapToDomainRoleSet(Set<RoleJpa> jpaUsers) {
        return jpaUsers.stream()
                .map(RoleJpaMapper::mapToDomainRole)
                .collect(Collectors.toSet());
    }

    public static Set<RoleJpa> mapToJpaRoleSet(Set<Role> users) {
        return users.stream()
                .map(RoleJpaMapper::mapToJpaRole)
                .collect(Collectors.toSet());
    }
}
