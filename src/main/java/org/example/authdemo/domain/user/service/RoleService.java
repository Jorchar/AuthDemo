package org.example.authdemo.domain.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.authdemo.domain.user.model.Action;
import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.domain.user.port.ActionRepository;
import org.example.authdemo.domain.user.port.RoleRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final ActionRepository actionRepository;
    private final ActionServiceClient actionServiceClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Cacheable("actions")
    public Map<String, List<Action>> getRoleActions() {
        log.info("Load data {}", 1);
        var roles = roleRepository.getAllRoles();
        var rolesNames = roles.stream().map(Role::getName).toList();
        return actionServiceClient.getRoleActions(rolesNames);
    }
}
