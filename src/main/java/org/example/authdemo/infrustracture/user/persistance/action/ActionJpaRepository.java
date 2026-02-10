package org.example.authdemo.infrustracture.user.persistance.action;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActionJpaRepository extends JpaRepository<ActionJpa, Long> {
    List<ActionJpa> findByRole_Name(String roleName);
}
