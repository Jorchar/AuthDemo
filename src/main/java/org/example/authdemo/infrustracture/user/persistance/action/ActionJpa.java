package org.example.authdemo.infrustracture.user.persistance.action;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.authdemo.infrustracture.user.persistance.role.RoleJpa;

@Entity
@Table(name = "actions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ActionJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleJpa role;
}
