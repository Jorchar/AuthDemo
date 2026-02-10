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
        User user = User.builder().firstName("Krzysztof").lastName("Kowalski").build();
        User createdUser = User.builder().firstName("Krzysztof").lastName("Kowalski").build();
        when(userRepository.save(user)).thenReturn(createdUser);

        // When
        var newlyCreatedUser = userService.createUser(user);

        // Then
        assertEquals(createdUser.getFirstName(), newlyCreatedUser.getFirstName());
        verify(userRepository).save(user);
    }

    @Test
    void shouldGetUserByEmail() {
        // Given
        User user = User.builder().firstName("Jan").lastName("Nowak").build();
        when(userRepository.getUserByEmail("Jan")).thenReturn(user);

        // When
        var result = userService.getUserByEmail("Jan");

        // Then
        assertEquals("Jan", result.getFirstName());
        verify(userRepository).getUserByEmail("Jan");
    }

    @Test
    void shouldGetAllUsers() {
        /*// Given
        List<User> users = List.of(
                User.builder().firstName("Jan").lastName("Nowak").build(),
                User.builder().firstName("Anna").lastName("Kowalska").build()
        );
        when(userRepository.getAllUsers()).thenReturn(users);

        // When
        var result = userService.getAllUsers();

        // Then
        assertEquals(2, result.size());
        verify(userRepository).getAllUsers();*/
    }
}