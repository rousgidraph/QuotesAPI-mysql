package com.example.quotesapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationResponse {
    Long verifierId;
    String verifiedBy;
    Long quoteID;
    String quoteLink;
}
