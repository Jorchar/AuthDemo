package org.example.authdemo.domain.user.service;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.Action;
import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.domain.user.port.ActionRepository;
import org.example.authdemo.domain.user.port.RoleRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final ActionRepository actionRepository;

    @Cacheable("actions")
    public Map<String, List<Action>> getRoleActions() {
        var roles = roleRepository.getAllRoles();
        var rolesNames = roles.stream().map(Role::getName).toList();
        return actionRepository.getRoleActions(rolesNames);
    }
}
