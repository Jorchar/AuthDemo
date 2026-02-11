package org.example.authdemo.domain.user.service;

import org.example.authdemo.domain.user.model.Action;

import java.util.List;
import java.util.Map;

public interface ActionServiceClient {
    Map<String, List<Action>> getRoleActions(List<String> roleNames);
}
