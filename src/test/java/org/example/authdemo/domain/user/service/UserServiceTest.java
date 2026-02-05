package org.example.authdemo.domain.user.service;

import org.example.authdemo.domain.user.model.User;
import org.example.authdemo.domain.user.port.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        // Given
        User user = User.builder()
                .firstName("Krzysztof")
                .lastName("Kowalski")
                .build();

        User createdUser = User.builder()
                .firstName("Krzysztof")
                .lastName("Kowalski")
                .build();

        when(userRepository.save(user)).thenReturn(createdUser);

        // When
        var newlyCreatedUser = userService.createUser(user);

        // Then
        assertEquals(createdUser.getFirstName(), newlyCreatedUser.getFirstName());
        verify(userRepository).save(user);
    }

    @Test
    void shouldGetUserByFirstName() {
        // Given
        User user = User.builder().firstName("Jan").lastName("Nowak").build();
        when(userRepository.getUserByFirstName("Jan")).thenReturn(user);

        // When
        var result = userService.getUserByFirstName("Jan");

        // Then
        assertEquals("Jan", result.getFirstName());
        verify(userRepository).getUserByFirstName("Jan");
    }

    @Test
    void shouldGetAllUsers() {
        // Given
        List<User> users = List.of(
                User.builder().firstName("Jan").lastName("Nowak").build(),
                User.builder().firstName("Anna").lastName("Kowalska").build()
        );
        when(userRepository.getAllUsers()).thenReturn(users);

        // When
        var result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        verify(userRepository).getAllUsers();
    }

    @Test
    void shouldPatchUserFirstNameByEmail() {
        // Given
        String email = "jan.nowak@mail.com";
        User patchedUser = User.builder().firstName("Janusz").lastName("Nowak").email(email).build();
        when(userRepository.patchUserFirstNameByEmail("Janusz", email)).thenReturn(patchedUser);

        // When
        var result = userService.patchUserFirstNameByEmail("Janusz", email);

        // Then
        assertEquals("Janusz", result.getFirstName());
        assertEquals(email, result.getEmail());
        verify(userRepository).patchUserFirstNameByEmail("Janusz", email);
    }

    @Test
    void shouldDeleteUserByEmail() {
        // Given
        String email = "anna.kowalska@mail.com";
        doNothing().when(userRepository).deleteUserByEmail(email);

        // When
        userService.deleteUserByEmail(email);

        // Then
        verify(userRepository).deleteUserByEmail(email);
    }

    @Test
    void shouldUpdateUser() {
        // Given
        User domainUser = User.builder()
                .firstName("Krzysztof")
                .lastName("Nowak")
                .email("krzysztof.nowak@mail.com")
                .build();

        when(userRepository.updateUser(domainUser)).thenReturn(domainUser);

        // When
        var result = userService.updateUser(domainUser);

        // Then
        assertEquals("Krzysztof", result.getFirstName());
        verify(userRepository).updateUser(domainUser);
    }
}