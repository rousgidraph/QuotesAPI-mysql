package com.example.quotesapi.Controllers;

import com.example.quotesapi.DTOs.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ErrorDTO> handleGenericErrors(Exception ex){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);

    }
}
