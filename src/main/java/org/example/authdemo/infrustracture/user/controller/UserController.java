package org.example.authdemo.infrustracture.user.controller;

import jakarta.validation.Valid;
import org.example.authdemo.domain.user.service.UserService;
import org.example.authdemo.infrustracture.user.controller.dto.RequestUserDto;
import org.example.authdemo.infrustracture.user.controller.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        var users = userService.getAllUsers();
        return ResponseEntity.ok(UserMapper.mapToUserDtoList(users));
    }

    @GetMapping("/users/{firstName}")
    public ResponseEntity<UserDto> getUserByFirstName(@PathVariable String firstName) {
        var user = userService.getUserByFirstName(firstName);
        return ResponseEntity.ok(UserMapper.mapToUserDto(user));
    }

    @PostMapping(path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody RequestUserDto request) {
        var domainUser = UserMapper.mapToDomainUser(request);
        var createdUser = userService.createUser(domainUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.mapToUserDto(createdUser));
    }

    @PutMapping(path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> updateUser(
            @Valid @RequestBody RequestUserDto request
    ) {
        var domainUser = UserMapper.mapToDomainUser(request);
        var patchedUser = userService.updateUser(domainUser);
        return ResponseEntity.ok(UserMapper.mapToUserDto(patchedUser));
    }

    @PatchMapping("/users/{email}/first-name")
    public ResponseEntity<UserDto> updateFirstNameByEmail(
            @PathVariable String email,
            @RequestParam String firstName
    ) {
        var patchedUser = userService.patchUserFirstNameByEmail(firstName, email);
        return ResponseEntity.ok(UserMapper.mapToUserDto(patchedUser));
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity deleteUser(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }
}
