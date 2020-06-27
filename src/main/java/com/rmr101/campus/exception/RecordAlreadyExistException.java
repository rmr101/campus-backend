package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class RecordAlreadyExistException extends RuntimeException{
    private String errorMessage = "Id not Found";
}
