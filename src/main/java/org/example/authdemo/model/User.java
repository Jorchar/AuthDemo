package org.example.authdemo.model;

import lombok.Builder;

import java.util.UUID;

@Builder
public record User(
        UUID uuid,
        String firstname,
        String lastname
) {

}
