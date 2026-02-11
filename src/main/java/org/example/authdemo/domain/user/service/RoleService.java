package org.example.authdemo.domain.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.authdemo.domain.user.model.Role;
import org.example.authdemo.domain.user.port.ActionRepository;
import org.example.authdemo.domain.user.port.RoleRepository;
import org.example.authdemo.infrustracture.user.controller.dto.ActionDto;
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
    public Map<String, List<ActionDto>> getRoleActions() {

        var roleNames = roleRepository.getAllRoles()
                .stream()
                .map(Role::getName)
                .toList();

        var roleActions = actionServiceClient.getRoleActions(roleNames);
        var rolActionsDTO = objectMapper.convertValue(
                roleActions,
                new TypeReference<Map<String, List<ActionDto>>>() {}
        );
        log.info("Loaded data: {}", rolActionsDTO);

        return rolActionsDTO;
    }
}
