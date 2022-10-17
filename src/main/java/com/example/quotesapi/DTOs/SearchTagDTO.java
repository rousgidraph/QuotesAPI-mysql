package com.example.quotesapi.DTOs;

import com.example.quotesapi.Validations.SearchTagValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class SearchTagDTO {
    private Long tagId;
    private String tag;
}
