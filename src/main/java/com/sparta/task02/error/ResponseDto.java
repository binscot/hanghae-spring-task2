package com.sparta.task02.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {
    private final boolean success;
    private final T response;
    private final ApiError apiError;

    public ResponseDto(boolean success, T response, ApiError apiError){
        this.success =success;
        this.response =response;
        this.apiError = apiError;
    }
}
