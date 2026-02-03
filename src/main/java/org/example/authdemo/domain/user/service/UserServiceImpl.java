package org.example.authdemo.domain.user.service;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.User;
import org.example.authdemo.domain.user.port.UserRepository;
import org.example.authdemo.domain.user.port.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User patchUserFirstNameByLastName(String firstName, String lastName) {
        return userRepository.patchUserFirstNameByLastName(firstName, lastName);
    }
}
