package com.example.quotesapi.DTOs;


import com.example.quotesapi.Validations.SearchTagValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SearchTagValidator(message = "Tag Id or tag must be present. NB not both") // TODO: 13/10/2022 Confirm this is working well.
public class TagQuoteDTO {
    Long quoteId;
    Long tagId;
    String tag;
    String status;

}
