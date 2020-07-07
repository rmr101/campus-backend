package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class RecordAlreadyExistException extends RuntimeException{
    private String errorMessage = "Record already exist";
    private String detail;

    public RecordAlreadyExistException(String detail) {
        this.detail = detail;
    }

    public RecordAlreadyExistException() {
    }
}