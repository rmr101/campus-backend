package com.rmr101.campus.exception;

import lombok.Data;
import org.aspectj.bridge.Message;

@Data
public class InvalidIdException extends RuntimeException{
    private String message = "Id not Found";
}
