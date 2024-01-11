package com.ggonzalez.farmtest.exception;

import com.ggonzalez.farmtest.exception.FarmErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FarmExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<FarmErrorResponse> handleException(Exception exc){

        FarmErrorResponse error = new FarmErrorResponse();
        error.setMessage(exc.getMessage());
        error.setStatus(HttpStatus.PRECONDITION_FAILED.value());

        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }
}
