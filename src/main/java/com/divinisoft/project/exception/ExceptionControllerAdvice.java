package com.divinisoft.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({VacationValidationException.class})
    public ResponseEntity<Object> handle(VacationValidationException e) {
    	return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
