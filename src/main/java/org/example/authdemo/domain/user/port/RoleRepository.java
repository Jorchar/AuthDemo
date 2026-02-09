package org.example.authdemo.domain.user.port;

import org.example.authdemo.domain.user.model.Role;

public interface RoleRepository {
    Role getDefaultRole();
}
