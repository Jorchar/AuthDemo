package org.example.authdemo.domain.user.port;

import org.example.authdemo.domain.user.model.User;

import java.util.List;

public interface UserRepository {
    User getUserByEmail(String email);

    List<User> getAllUsers();

    User save(User user);

    User patchUserFirstNameByEmail(String firstName, String email);

    void deleteUserByEmail(String email);

    User updateUser(User domainUser);
}
