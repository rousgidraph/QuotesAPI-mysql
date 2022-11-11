package com.example.quotesapi.Controllers;

import com.example.quotesapi.DTOs.QuotesDTO;
import com.example.quotesapi.DTOs.SuccessfulSubmissionDTO;
import com.example.quotesapi.DTOs.TagQuoteDTO;
import com.example.quotesapi.DTOs.VerificationResponse;
import com.example.quotesapi.Exceptions.QuoteNotFoundException;
import com.example.quotesapi.Exceptions.UserNotFoundException;
import com.example.quotesapi.Services.QuoteService;
import com.example.quotesapi.Utils.HttpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.CloseableThreadContext;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "api/v1/quote")
@RequiredArgsConstructor
@Slf4j
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
        try(final CloseableThreadContext.Instance ignored = CloseableThreadContext.put("Source Ip", HttpUtils.getClientIpAddressIfServletRequestExist())) {
//        MDC.put("Source Ip", HttpUtils.getClientIpAddressIfServletRequestExist());
            ResponseEntity<QuotesDTO> quotesDTOResponseEntity = new ResponseEntity<>(quoteService.getQuote(quoteId), HttpStatus.OK);
//        MDC.clear();
            return quotesDTOResponseEntity;
        }
    }

    @GetMapping(path = "find")
    public ResponseEntity<List<QuotesDTO>>  getQuotesByTag(@RequestParam String searchWord){

        List<QuotesDTO> byTag = quoteService.getQuotesByTag(searchWord);

        return new ResponseEntity<>(byTag,HttpStatus.OK);
    }

    @PostMapping("addTags")
    public ResponseEntity<TagQuoteDTO> tagQuote(@RequestBody @Valid TagQuoteDTO tagQuoteDTO) throws Exception {
        TagQuoteDTO result = quoteService.tagQuote(tagQuoteDTO);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    private String getBaseUrl(HttpServletRequest request){
        return ServletUriComponentsBuilder.fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
    }
}
