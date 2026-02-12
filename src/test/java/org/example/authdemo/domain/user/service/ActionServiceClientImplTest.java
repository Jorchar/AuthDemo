package org.example.authdemo.domain.user.service;

import org.example.authdemo.AuthDemoApplication;
import org.example.authdemo.domain.user.model.Action;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.wiremock.spring.EnableWireMock;

import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AuthDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock
class ActionServiceClientImplTest {
    @Value("${wiremock.server.baseUrl}")
    private String wireMockUrl;

    @Autowired
    private ActionServiceClient actionServiceClient;

    @Test
    void givenWireMockStub_whenGetRoleActions_thenReturnsMap() {


        stubFor(get(urlEqualTo("/api/actions"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                      "USER": [
                                        {
                                          "id": 1,
                                          "name": "USER_READ",
                                          "description": "reading user details"
                                        }
                                      ],
                                      "ADMIN": [
                                        {
                                          "id": 2,
                                          "name": "USER_WRITE",
                                          "description": "change user details"
                                        },
                                        {
                                          "id": 3,
                                          "name": "USER_DELETE",
                                          "description": "remove user details"
                                        }
                                      ]
                                    }
                                """
                        )));

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Map<String, List<Action>>> response =
            restTemplate.exchange(
                wireMockUrl + "/api/actions",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<String, List<Action>>>(){}
            );

        Map<String, List<Action>> body = response.getBody();

        assertNotNull(body);
        assertTrue(body.containsKey("USER"));
        assertTrue(body.containsKey("ADMIN"));
        assertEquals(1, body.get("USER").size());
        assertEquals("USER_READ", body.get("USER").get(0).getName());
    }
}