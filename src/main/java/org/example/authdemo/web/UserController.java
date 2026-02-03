package org.example.authdemo.web;

import jakarta.validation.Valid;
import org.example.authdemo.domain.UserRepository;
import org.example.authdemo.model.RequestCreateUserDto;
import org.example.authdemo.model.User;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping(path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> createUser(@Valid @RequestBody RequestCreateUserDto request) {
        User user = new User(
                request.firstName(),
                request.lastName()
        );
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    /*@PutMapping("/users/{lastName}")
    public ResponseEntity<User>*/

    @PatchMapping("/users/{lastName}/first-name")
    public ResponseEntity<User> updateFirstNameByLastName(
            @PathVariable String lastName,
            @RequestParam String firstName
    ) {
        User user = userRepository.findFirstByLastName(lastName)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
        user.setFirstName(firstName);
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
