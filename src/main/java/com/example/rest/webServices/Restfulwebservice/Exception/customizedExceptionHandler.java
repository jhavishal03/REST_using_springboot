package com.example.rest.webServices.Restfulwebservice.Exception;

import com.example.rest.webServices.Restfulwebservice.User.userNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
@ControllerAdvice
@RestController
public class customizedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object>
         handleAllExceptions(Exception ex, WebRequest request) throws Exception {
    ExceptionResponse exceptionResponse=
            new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
   return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(userNotFoundException.class)
    public final ResponseEntity<Object>
    handleUserNotFoundExceptions(userNotFoundException ex, WebRequest request) throws Exception {
        ExceptionResponse exceptionResponse= new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionResponse exceptionResponse=
                new ExceptionResponse(new Date(),"Name should contain atleast 2 charachters",
                        ex.getLocalizedMessage().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
        //        return this.handleExceptionInternal(ex, (Object)null, headers, status, request);
    }

}