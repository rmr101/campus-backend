package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class UnauthorizedException extends RuntimeException{
    private String errorMessage = "Action not authorized";
    private String detail;

    public UnauthorizedException(String detail) {
        this.detail = detail;
    }

    public UnauthorizedException() {
    }
}
