package org.example.authdemo.domain.user.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    String firstName;

    String lastName;

    String email;

    Instant createdAt;

    Instant updatedAt;
}
