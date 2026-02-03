package org.example.authdemo.infrustracture.user.controller;

import org.example.authdemo.domain.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapToDomainUser(RequestCreateUserDto user) {
        return User.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .build();
    }

    public UserDto mapToDto(User user) {
        return UserDto.builder().build();
    }
}
