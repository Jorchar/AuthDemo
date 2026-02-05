package org.example.authdemo.infrustracture.user.controller;

import org.example.authdemo.domain.user.model.User;
import org.example.authdemo.infrustracture.user.controller.dto.RequestUserDto;
import org.example.authdemo.infrustracture.user.controller.dto.UserDto;

import java.util.List;

public final class UserMapper {
    public static User mapToDomainUser(RequestUserDto user) {
        return User.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .email(user.email())
                .build();
    }

    public static User mapToDomainUser(UserDto user) {
        return User.builder()
                .firstName(user.firstName())
                .lastName(user.lastName())
                .email(user.email())
                .createdAt(user.createdAt())
                .updatedAt(user.updatedAt())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }


    public static List<User> mapToDomainUserList(List<UserDto> usersDto) {
        return usersDto.stream()
                .map(UserMapper::mapToDomainUser)
                .toList();
    }

    public static List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }
}
