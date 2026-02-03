package org.example.authdemo.infrustracture.user.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
    Optional<UserJpa> findByFirstName(String firstName);

    Optional<UserJpa> findFirstByLastName(String firstName);

    Optional<UserJpa> findDistinctPersonByLastName(String LastName);
}
