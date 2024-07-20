package com.projects.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalTime;

@ControllerAdvice
public class GobalExceptionHandler {

    //handle specific exception
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ErrorDetails> handleAccountException(Exception exception  ,WebRequest webrequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalTime.now(),
                exception.getMessage(),
                webrequest.getDescription(false),
                "ACCOUNT_NOT_fOUND"

        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenricException(Exception exception  ,WebRequest webrequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalTime.now(),
                exception.getMessage(),
                webrequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"

        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
