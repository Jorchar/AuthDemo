package org.example.authdemo.infrustracture.user.persistance.role;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public final class RoleJpaMapper {

    public static Role mapToDomainRole(RoleJpa role) {
        return Role.builder()
                .name(role.getName())
                .build();
    }

    public static Set<Role> mapToDomainRoleSet(Set<RoleJpa> jpaUsers) {
        return jpaUsers.stream()
                .map(RoleJpaMapper::mapToDomainRole)
                .collect(Collectors.toSet());
    }
}
