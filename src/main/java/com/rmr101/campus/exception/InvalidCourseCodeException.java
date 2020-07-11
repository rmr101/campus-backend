package com.rmr101.campus.exception;

import lombok.Data;

@Data
public class InvalidCourseCodeException extends RuntimeException{
    private String errorMessage = "Course Code not Found";
    private String detail;

    public InvalidCourseCodeException(String detail) {
        this.detail = detail;
    }

    public InvalidCourseCodeException() {
    }
}
