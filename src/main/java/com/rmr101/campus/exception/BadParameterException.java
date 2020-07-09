package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class BadParameterException extends RuntimeException{
    private String errorMessage = "Parameter Illegal";
    private String detail;

    public BadParameterException(String detail) {
        this.detail = detail;
    }

    public BadParameterException() {
    }
}