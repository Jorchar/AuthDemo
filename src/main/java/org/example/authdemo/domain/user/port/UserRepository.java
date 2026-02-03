package org.example.authdemo.domain.user.port;

import org.example.authdemo.domain.user.model.User;

public interface UserRepository {
    User save(User user);

    User patchUserFirstNameByLastName(String firstName, String lastName);
}
