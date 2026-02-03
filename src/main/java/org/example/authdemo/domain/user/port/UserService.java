package org.example.authdemo.domain.user.port;

import org.example.authdemo.domain.user.model.User;

public interface UserService {
    User createUser(User user);

    User patchUserFirstNameByLastName(String firstName, String lastName);
}
