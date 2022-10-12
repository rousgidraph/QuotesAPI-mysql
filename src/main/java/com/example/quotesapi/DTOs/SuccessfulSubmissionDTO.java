package com.example.quotesapi.DTOs;

import com.example.quotesapi.Domains.quotes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulSubmissionDTO {
    private quotes submittedQuote;
    private String verificationLink;
}
