package com.example.quotesapi.Controllers;

import com.example.quotesapi.DTOs.SuccessfulSubmissionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "api/v1/quote")
public class QuoteController {

    @PostMapping(path = "newQuote")
    public ResponseEntity<SuccessfulSubmissionDTO> submitQuote(){
        throw new RuntimeException("This is a test ");
    }
    @GetMapping(path = "verify/{quoteId}/{userId}")
    public ResponseEntity<String> verifyQuote(@PathVariable Long quoteId , @PathVariable Long userId ){

        HttpHeaders headers= new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);


        return new ResponseEntity<>("Successfully ",headers , HttpStatus.OK);
    }

}
