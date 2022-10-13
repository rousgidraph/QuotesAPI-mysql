package com.example.quotesapi.Controllers;


import com.example.quotesapi.DTOs.UserDetailsDTO;
import com.example.quotesapi.Domains.UserDetails;
import com.example.quotesapi.Mappers.UserMapper;
import com.example.quotesapi.Services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "api/v1/user")
@Slf4j
public class UserController {
    @Autowired
    UserMapper userMapper;


    @Autowired
    UserService userService;
    @PostMapping(path = "addUser",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsDTO> addUser(@RequestBody UserDetailsDTO userDetails){
        return new ResponseEntity<>(userService.addUser(userDetails), HttpStatus.OK);
    }

    @GetMapping(path = "findUser/{userId}")
    public ResponseEntity<UserDetailsDTO> getUser(@PathVariable Long userId){

        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK) ;
    }


}
