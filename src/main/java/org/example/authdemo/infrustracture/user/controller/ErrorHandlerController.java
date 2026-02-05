package org.example.authdemo.infrustracture.user.controller;

import org.example.authdemo.infrustracture.user.controller.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleError(Exception exception) {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleError(DataIntegrityViolationException exception) {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        ErrorResponse body = new ErrorResponse("Bad request");
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleError(ResponseStatusException exception) {
        HttpStatusCode status = exception.getStatusCode();
        ErrorResponse body = new ErrorResponse(exception.getMessage());
        return ResponseEntity.status(status).body(body);
    }
}
