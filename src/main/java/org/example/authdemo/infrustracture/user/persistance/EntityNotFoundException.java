package org.example.authdemo.infrustracture.user.persistance;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
