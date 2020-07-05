package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class InvalidIdException extends RuntimeException{
    private String errorMessage = "Id not Found";
    private String detial;

    public InvalidIdException(String detial) {
        this.detial = detial;
    }

    public InvalidIdException() {
    }
}
