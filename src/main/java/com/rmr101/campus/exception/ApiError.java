package com.rmr101.campus.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {
    private HttpStatus status;
    private String error_code;
    private String message;
//    private String details;

    public ApiError(HttpStatus status) {
        this.status = status;
    }
}
