package org.example.authdemo.infrustracture.user.controller;

import jakarta.validation.Valid;
import org.example.authdemo.domain.user.service.UserServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    public UserController(UserServiceImpl userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping(path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody RequestCreateUserDto request) {
        var domainUser = userMapper.mapToDomainUser(request);
        var createdUser = userService.createUser(domainUser);
        return ResponseEntity.ok(userMapper.mapToDto(createdUser));
    }

    /*@PutMapping("/users/{lastName}")
    public ResponseEntity<User>*/

    @PatchMapping("/users/{lastName}/first-name")
    public ResponseEntity<UserDto> updateFirstNameByLastName(
            @PathVariable String lastName,
            @RequestParam String firstName
    ) {
        var patchedUser = userService.patchUserFirstNameByLastName(firstName, lastName);
        return ResponseEntity.ok(userMapper.mapToDto(patchedUser));
    }
}
