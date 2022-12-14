package com.example.quotesapi.DTOs;

import com.example.quotesapi.Domains.Quotes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessfulSubmissionDTO {
    private QuotesDTO submittedQuote;
    private String verificationLink;
}
