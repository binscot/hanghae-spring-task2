package com.sparta.task02.error;

public class ApiUtils {
    public static <T>ResponseDto<T> success(T response) {
        return new ResponseDto<>(true, response, null);
    }

    public static ResponseDto<?> error(String message, int status){
        return new ResponseDto<>(false, null, new ApiError(message, status));
    }
}
