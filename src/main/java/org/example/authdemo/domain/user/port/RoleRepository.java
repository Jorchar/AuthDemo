package org.example.authdemo.domain.user.port;

import org.example.authdemo.domain.user.model.Role;

import java.util.List;

public interface RoleRepository {
    Role getDefaultRole();

    List<Role> getAllRoles();
}
