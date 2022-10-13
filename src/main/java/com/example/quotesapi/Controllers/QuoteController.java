package com.example.quotesapi.Controllers;

import com.example.quotesapi.DTOs.QuotesDTO;
import com.example.quotesapi.DTOs.SuccessfulSubmissionDTO;
import com.example.quotesapi.DTOs.VerificationResponse;
import com.example.quotesapi.Exceptions.QuoteNotFoundException;
import com.example.quotesapi.Exceptions.UserNotFoundException;
import com.example.quotesapi.Services.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "api/v1/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final HttpServletRequest request;
    private final  QuoteService quoteService;
    @PostMapping(path = "newQuote")
    public ResponseEntity<SuccessfulSubmissionDTO> submitQuote(@RequestParam Long submittedBy, @RequestBody QuotesDTO quoteToAdd,  HttpServletRequest request) throws UserNotFoundException {
        QuotesDTO quotesDTO = quoteService.addQuote(quoteToAdd, submittedBy);
        String verificationLink = getBaseUrl(request)+"/api/v1/quote/verify?quoteId="+quotesDTO.getQuoteId();
        SuccessfulSubmissionDTO result = new SuccessfulSubmissionDTO(quotesDTO,verificationLink);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @GetMapping(path = "verify")
    public ResponseEntity<VerificationResponse> verifyQuote(@RequestParam  Long quoteId , @RequestParam Long userId ) throws Exception {

        String baseUrl = getBaseUrl(request);
        String viewUser = baseUrl+"/api/v1/user/findUser/";
        String viewQuote = baseUrl+"/api/v1/quote/find/";


        HttpHeaders headers= new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        VerificationResponse verificationResponse = quoteService.verifyQuote(userId, quoteId);

        verificationResponse.setVerifiedBy(viewUser+verificationResponse.getVerifierId());
        verificationResponse.setQuoteLink(viewQuote+verificationResponse.getQuoteID());

        return new ResponseEntity<>(verificationResponse,headers , HttpStatus.OK);
    }

    @GetMapping(path = "find/{quoteId}")
    public ResponseEntity<QuotesDTO> getQuote(@PathVariable Long quoteId) throws QuoteNotFoundException {

        return new ResponseEntity<>(quoteService.getQuote(quoteId),HttpStatus.OK);
    }

    private String getBaseUrl(HttpServletRequest request){
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
    }
}
