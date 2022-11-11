package com.example.quotesapi.Mappers;

import com.example.quotesapi.DTOs.QuotesDTO;
import com.example.quotesapi.Domains.Quotes;
import com.example.quotesapi.Repos.userDetailsRepository;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class QuoteMapper {
    // TODO: 13/10/2022 This can be abit more optimised by asking for the count directly from the db
    //  then lazy loading the tags

    @Autowired
    UserMapper userMapper;

    @BeforeMapping
    protected void enrichDTO(@MappingTarget QuotesDTO quotesDTO, Quotes quotes){
        quotesDTO.setQuoteTagsCount(quotes.getQuoteTags().size());
        quotesDTO.setVerifiersCount(quotes.getVerifiers().size());
        quotesDTO.setSubmittedBy(userMapper.UserDetailsToUserDetailsDTO(quotes.getSubmittedBy()));

    }

    public abstract QuotesDTO toDTO(Quotes quotes);

    public abstract Quotes toQuotes(QuotesDTO quotesDTO);


}
