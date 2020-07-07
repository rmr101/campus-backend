package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class InvalidIdException extends RuntimeException{
    private String errorMessage = "Id not Found";
    private String detail;

    public InvalidIdException(String detail) {
        this.detail = detail;
    }

    public InvalidIdException() {
    }
}
