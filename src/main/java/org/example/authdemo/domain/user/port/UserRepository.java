package org.example.authdemo.domain.user.port;

import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepository {
    User getUserByEmail(String email);

    Page<User> getAllUsers(Pageable pageable);

    User save(User user);

    User patchUserFirstNameByEmail(String firstName, String email);

    void deleteUserByEmail(String email);

    User updateUser(User domainUser);

    List<Role> addUserRole(User user, Role role);

    List<Role> removeUserRole(User user, Role role);
}
