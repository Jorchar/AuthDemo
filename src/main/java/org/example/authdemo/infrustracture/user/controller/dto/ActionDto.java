package org.example.authdemo.infrustracture.user.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record ActionDto(
        String name,
        String description
) {
}
