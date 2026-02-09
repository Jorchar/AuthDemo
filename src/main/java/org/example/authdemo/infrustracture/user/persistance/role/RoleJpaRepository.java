package org.example.authdemo.infrustracture.user.persistance.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleJpa, Long> {
    Optional<RoleJpa> findByName(String name);
}
