package org.example.authdemo.infrustracture.user.persistance;

import org.example.authdemo.domain.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserJpaMapper {
    public User mapToDomainUser(UserJpa user) {
        return User.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public UserJpa mapToJpa(User user) {
        return new UserJpa(
                user.getFirstName(),
                user.getLastName()
        );
    }
}
