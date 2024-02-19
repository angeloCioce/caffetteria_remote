package com.example.caffetteria.config;

import com.example.caffetteria.exceptionhandler.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
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

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> error500Handler(HttpRequestMethodNotSupportedException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Stai cercando di fare qualcosa di impossibile,+ per favore riprova in un altro modo.", HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> errorIllegalArgumente(IllegalArgumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse("La cosa che stai cercando non esiste o non è disponibile.", HttpStatus.NOT_FOUND);
        return ResponseEntity.badRequest().body(errorResponse);
    }
}