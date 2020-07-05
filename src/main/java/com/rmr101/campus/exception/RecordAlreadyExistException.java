package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class RecordAlreadyExistException extends RuntimeException{
    private String errorMessage = "Record already exist";
    private String detial;

    public RecordAlreadyExistException(String detial) {
        this.detial = detial;
    }

    public RecordAlreadyExistException() {
    }
}