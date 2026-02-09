package org.example.authdemo.infrustracture.user.persistance.role;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.domain.user.port.RoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@AllArgsConstructor
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Role getDefaultRole() {
        RoleJpa roleJpa = roleJpaRepository.findByName("USER")
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User role not found"));
        return RoleJpaMapper.mapToDomainRole(roleJpa);
    }
}
