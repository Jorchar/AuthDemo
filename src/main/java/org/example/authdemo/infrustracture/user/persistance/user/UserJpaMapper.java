package org.example.authdemo.infrustracture.user.persistance.user;

import org.example.authdemo.domain.user.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public final class UserJpaMapper {
    public static User mapToDomainUser(UserJpa user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public static UserJpa mapToJpaUser(User user) {
        return new UserJpa(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static List<User> mapToDomainUserList(List<UserJpa> jpaUsers) {
        return jpaUsers.stream()
                .map(UserJpaMapper::mapToDomainUser)
                .toList();
    }

    public static List<UserJpa> mapToJpaUserList(List<User> users) {
        return users.stream()
                .map(UserJpaMapper::mapToJpaUser)
                .toList();
    }
}
