package com.projects.banking.advices;

import com.projects.banking.exception.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GobalExceptionHandler {

    //handle specific exception
    @ExceptionHandler(AccountException.class)
    public ResponseEntity<ApiResponse<?>> handleAccountException(Exception exception  , WebRequest webrequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalTime.now(),
                Collections.singletonList(exception.getMessage()),
                webrequest.getDescription(false),
                "ACCOUNT_NOT_fOUND"

        );
        return buildApiResposeEntity(errorDetails, HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenricException(Exception exception  ,WebRequest webrequest){
        ErrorDetails errorDetails = new ErrorDetails(
                LocalTime.now(),
                Collections.singletonList(exception.getMessage()),
                webrequest.getDescription(false),
                "INTERNAL_SERVER_ERROR"

        );
        return buildApiResposeEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>>handleValidtionException(MethodArgumentNotValidException exception,WebRequest webrequest){
         ApiResponse<?> obj = new ApiResponse<>(LocalTime.now());
          List<String>  errors = exception
                .getBindingResult()
                .getAllErrors().
                stream().
                map(error->error.getDefaultMessage()).collect(Collectors.toList());

        ErrorDetails errorDetails = new ErrorDetails(
                LocalTime.now(),
                errors,
                webrequest.getDescription(false),
                "BAD_REQUEST"

        );
        return buildApiResposeEntity(errorDetails,HttpStatus.BAD_REQUEST);

    }
    private ResponseEntity<ApiResponse<?>> buildApiResposeEntity(ErrorDetails errorDetails, HttpStatus status) {
        return  new ResponseEntity<>( new ApiResponse<>(errorDetails),status);
    }
}
