package com.rmr101.campus.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //400
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Illegal argument received")
//    @ExceptionHandler(IllegalArgumentException.class)
//    public void illegalArgument() {
//        log.error("Request raised a IllegalArgumentException");
//    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ApiErrorResponse handleUnauthorizedException(UnauthorizedException ex) {
        log.error("Request raised a InvalidIdException reason= " + ex.getDetial());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.UNAUTHORIZED)
                .withError_code("401")
                .withMessage(ex.getErrorMessage())
                .withDetail(ex.getDetial())
                .build();
        return response;
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ApiErrorResponse handleBadCredentialsException(BadCredentialsException ex) {
        log.error("Request raised a BadCredentialsException reason= " + ex.getMessage());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.UNAUTHORIZED)
                .withError_code("401")
                .withMessage(ex.getMessage())
                .withDetail(ex.getLocalizedMessage())
                .build();
        return response;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidIdException.class)
    public ApiErrorResponse handleInvalidIdException(InvalidIdException ex) {
        log.error("Request raised a InvalidIdException reason= " + ex.getDetial());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.NOT_FOUND)
                .withError_code("404")
                .withMessage(ex.getErrorMessage())
                .withDetail(ex.getDetial())
                .build();
        return response;
    }

    // 409
//    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public void conflict() {
//        log.error("Request raised a DataIntegrityViolationException");
//        //todo:do something here
//    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error("Request raised a InvalidIdException reason= " + ex.getMessage());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.CONFLICT)
                .withError_code("409")
//                .withMessage(ex.getMessage())
//                .withDetail(ex.getLocalizedMessage())
                .build();
        return response;
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(RecordAlreadyExistException.class)
    public ApiErrorResponse handleRecordAlreadyExistException(RecordAlreadyExistException ex) {
        log.error("Request raised a RecordAlreadyExistException, reason = " + ex.getDetial());
        ApiErrorResponse response =new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.CONFLICT)
                .withError_code("409")
                .withMessage(ex.getErrorMessage())
                .withDetail(ex.getDetial())
                .build();
        return response;
    }
}
