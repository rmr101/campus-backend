package com.rmr101.campus.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.UUID;

@Data
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No student found with this provided ID")
@AllArgsConstructor
public class StudentIdNotFoundException extends RuntimeException{

  UUID invalid_Id;
  Date timestamp;
  String message;

}
