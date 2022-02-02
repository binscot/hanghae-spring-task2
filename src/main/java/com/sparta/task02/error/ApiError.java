package com.sparta.task02.error;

public class ApiError {
    private final String message;
    private final int status;

    public ApiError(String message, int status){
        this.message = message;
        this.status = status;
    }
}
