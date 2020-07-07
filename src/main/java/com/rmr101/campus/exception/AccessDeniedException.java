package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class AccessDeniedException extends RuntimeException{
    private String errorMessage = "Access Denied";
    private String detail;

    public AccessDeniedException(String detail) {
        this.detail = detail;
    }

    public AccessDeniedException() {
    }
}
