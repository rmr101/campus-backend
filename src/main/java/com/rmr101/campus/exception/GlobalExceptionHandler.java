package com.rmr101.campus.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //400
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal argument received")
    @ExceptionHandler(IllegalArgumentException.class)
    public void illegalArgument() {
        log.error("Request raised a IllegalArgumentException");
    }

    // 401
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Not Authorized")
    @ExceptionHandler(UnauthorizedException.class)
    public void unauthorized() {
        log.error("Request raised a UnauthorizedException");
    }

    // 401
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Bad Credentials")
    @ExceptionHandler(BadCredentialsException.class)
    public void loginFail() {
        log.error("Request raised a BadCredentialsException caused by incorrect username or password");
    }

    //404
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid Id")
    @ExceptionHandler(InvalidIdException.class)
    public void invalidId() {
        log.error("Request raised a InvalidIdException");
    }

    // 409
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
    @ExceptionHandler(DataIntegrityViolationException.class)
    public void conflict() {
        log.error("Request raised a DataIntegrityViolationException");
        // Nothing to do
    }

    // 409
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Record already exist")
    @ExceptionHandler(RecordAlreadyExistException.class)
    public void recordExist() {
        log.error("Request raised a RecordAlreadyExistException");
    }

}
