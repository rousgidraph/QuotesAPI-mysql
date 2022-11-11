package com.example.quotesapi.Controllers;

import com.example.quotesapi.DTOs.ErrorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleGenericErrors(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {


        int size = ex.getBindingResult().getFieldErrors().size();
        int size1 = ex.getAllErrors().size();
        List<String> errors = new ArrayList<>();
        String errorString ="";
        if(size1 > 0){
            ex.getAllErrors().forEach(objectError -> {
                errors.add(objectError.getDefaultMessage());
            });

        }else if(size > 0){
            ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
                errors.add( fieldError.getField() + fieldError.getDefaultMessage());
            });
        }

        errorString = String.join(",",errors);
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(errorString);
//        errorDTO.setErrorCode(333);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleGenericErrors(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleGenericErrors(ex);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handleGenericErrors(Exception ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(ex.getLocalizedMessage());
//        errorDTO.setErrorCode(333);
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }


}
