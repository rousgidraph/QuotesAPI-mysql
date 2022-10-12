package com.example.quotesapi.DTOs;

import com.example.quotesapi.Domains.Quotes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessfulSubmissionDTO {
    private Quotes submittedQuote;
    private String verificationLink;
}
