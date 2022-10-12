package com.example.quotesapi.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ErrorDTO {
    private String errorMessage;
    private int errorCode = 400;
}
