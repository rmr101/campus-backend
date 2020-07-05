package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class UnauthorizedException extends RuntimeException{
    private String errorMessage = "Action not authorized";
    private String detial;

    public UnauthorizedException(String detial) {
        this.detial = detial;
    }

    public UnauthorizedException() {
    }
}
