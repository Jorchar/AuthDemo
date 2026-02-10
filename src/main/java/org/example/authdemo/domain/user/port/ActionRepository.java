package org.example.authdemo.domain.user.port;

import org.example.authdemo.domain.user.model.Action;

import java.util.List;
import java.util.Map;

public interface ActionRepository {
    Map<String, List<Action>> getRoleActions(List<String> roles);
}
