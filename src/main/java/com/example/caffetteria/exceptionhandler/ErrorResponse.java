package com.example.caffetteria.exceptionhandler;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private final String errorMessage;
    private final String errorCode;

    public ErrorResponse(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

}
