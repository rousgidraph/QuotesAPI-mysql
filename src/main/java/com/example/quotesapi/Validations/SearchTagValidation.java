package com.example.quotesapi.Validations;

import com.example.quotesapi.DTOs.SearchTagDTO;
import com.example.quotesapi.DTOs.TagQuoteDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SearchTagValidation implements ConstraintValidator<SearchTagValidator, TagQuoteDTO> {

    @Override
    public void initialize(SearchTagValidator constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(TagQuoteDTO value, ConstraintValidatorContext context) {
        if(value.getTag() == null && value.getTagId() == null){
            return false;
        }else if(value.getTag() != null && value.getTagId() != null){
            return false;
        }
        else{
            return true;
        }
    }
}
