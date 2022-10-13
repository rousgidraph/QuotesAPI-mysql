package com.example.quotesapi.Controllers;

import com.example.quotesapi.DTOs.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

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
