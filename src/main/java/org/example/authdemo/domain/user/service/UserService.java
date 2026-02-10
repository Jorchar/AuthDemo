package org.example.authdemo.domain.user.service;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.domain.user.model.User;
import org.example.authdemo.domain.user.port.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User patchUserFirstNameByEmail(String firstName, String email) {
        return userRepository.patchUserFirstNameByEmail(firstName, email);
    }

    public void deleteUserByEmail(String email) {
        userRepository.deleteUserByEmail(email);
    }

    public User updateUser(User domainUser) {
        return userRepository.updateUser(domainUser);
    }

    public List<Role> addUserRole(User user, Role role) {
        return userRepository.addUserRole(user, role);
    }

    public List<Role> removeUserRole(User user, Role role) {
        return userRepository.removeUserRole(user, role);
    }
}
