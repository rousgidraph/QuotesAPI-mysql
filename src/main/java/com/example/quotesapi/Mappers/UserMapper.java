package com.example.quotesapi.Mappers;

import com.example.quotesapi.DTOs.UserDetailsDTO;
import com.example.quotesapi.Domains.UserDetails;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class   UserMapper {
    // TODO: 13/10/2022 This can be abit more optimised by asking for the count directly from the db
    //  then lazy loading the quotes
    @BeforeMapping
    protected void enrichDTO(@MappingTarget UserDetailsDTO userDetailsDTO,UserDetails details){
        userDetailsDTO.setSubmittedQuotesCount(details.getSubmittedQuotes().size());
    }
    public abstract UserDetailsDTO UserDetailsToUserDetailsDTO(UserDetails details);

    public abstract UserDetails userDetailsDTOToUserDetails(UserDetailsDTO detailsDTO);
}
