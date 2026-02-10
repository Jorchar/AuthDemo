package org.example.authdemo.infrustracture.user.persistance.action;

import org.example.authdemo.domain.user.model.Action;
import org.springframework.stereotype.Component;

@Component
public final class ActionJpaMapper {
    public static Action mapToDomainAction(ActionJpa actionJpa) {
        return Action.builder()
                .id(actionJpa.getId())
                .name(actionJpa.getName())
                .description(actionJpa.getDescription())
                .build();
    }

    public static ActionJpa mapToJpaAction(Action action) {
        return new ActionJpa();
    }
}
