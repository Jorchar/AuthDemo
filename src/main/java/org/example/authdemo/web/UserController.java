package org.example.authdemo.web;

import jakarta.validation.Valid;
import org.example.authdemo.model.RequestCreateUserDto;
import org.example.authdemo.model.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping(path = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public User createUser(@Valid @RequestBody RequestCreateUserDto request) {
        return User.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .build();
    }
}
