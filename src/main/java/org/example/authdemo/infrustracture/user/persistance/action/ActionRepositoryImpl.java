package org.example.authdemo.infrustracture.user.persistance.action;

import lombok.AllArgsConstructor;
import org.example.authdemo.domain.user.model.Action;
import org.example.authdemo.domain.user.port.ActionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ActionRepositoryImpl implements ActionRepository {
    private final ActionJpaRepository actionJpaRepository;

    @Override
    public Map<String, List<Action>> getRoleActions(List<String> roles) {
        return roles.stream().collect(Collectors.toMap(
                role -> role,
                role -> actionJpaRepository.findByRole_Name(role)
                        .stream()
                        .map(ActionJpaMapper::mapToDomainAction)
                        .toList()
        ));
    }
}
