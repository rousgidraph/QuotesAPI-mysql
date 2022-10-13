package com.example.quotesapi.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetailsDTO {
    private Long userId;
    private String firstName;
    private String secondName;
    private String email;
    private Date dateJoined;

    private int submittedQuotesCount;


    @Override
    public String toString() {
        return "{\n" +
                "    \"userId\": "+userId+",\n" +
                "    \"firstName\": \""+firstName+"\",\n" +
                "    \"secondName\": \""+secondName+"\",\n" +
                "    \"email\": \""+email+"\",\n" +
                "    \"dateJoined\": "+dateJoined+"\n" +
                "    \"SubmittedQuotes\": "+submittedQuotesCount+"\n" +
                "}";
    }
}
