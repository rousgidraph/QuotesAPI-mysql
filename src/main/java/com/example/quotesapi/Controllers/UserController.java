package com.example.quotesapi.Controllers;


import com.example.quotesapi.DTOs.UserDetailsDTO;
import com.example.quotesapi.Domains.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "api/v1/quote")
@Slf4j
public class UserController {

    @PostMapping(path = "addUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<UserDetailsDTO> addUser(@RequestBody UserDetailsDTO userDetails){
        log.info("Recieved user {}",userDetails);
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
}
