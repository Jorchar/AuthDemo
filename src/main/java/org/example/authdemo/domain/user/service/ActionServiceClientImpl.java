package org.example.authdemo.domain.user.service;

import org.example.authdemo.domain.user.model.Action;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class ActionServiceClientImpl implements  ActionServiceClient {

    @Override
    public Map<String, List<Action>> getRoleActions(List<String> roleNames) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map<String, List<Action>>> response = restTemplate.exchange(
                "http://localhost:8085/api/actions", HttpMethod.GET, null,
                new ParameterizedTypeReference<Map<String, List<Action>>>(){});

        return response.getBody();
    }
}
