package com.example.quotesapi.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuotesDTO {

    private Long quoteId;
    private String quoteStatement;
    private int quoteTagsCount;
    private int verifiersCount;
    private UserDetailsDTO submittedBy;
    private Date dateSubmitted;

}
