package com.example.caffetteria.config;

import com.example.caffetteria.exceptionhandler.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>("Accesso negato: Non hai i permessi necessari per accedere a questa risorsa. Logga o registrati per continuare", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ErrorResponse> handleCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Accesso negato: Non hai i permessi necessari per accedere a questa risorsa. Logga o registrati per continuare", HttpStatus.FORBIDDEN);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}