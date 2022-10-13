package com.example.quotesapi.Services;

import com.example.quotesapi.DTOs.UserDetailsDTO;
import com.example.quotesapi.Domains.UserDetails;
import com.example.quotesapi.Mappers.UserMapper;
import com.example.quotesapi.Repos.userDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final userDetailsRepository userDetailsRepo;

    @Autowired
    UserMapper userMapper;

    public UserDetailsDTO getUser(long userId){
        Optional<UserDetails> details = userDetailsRepo.findById(userId);
        if (details.isEmpty()){
            throw new RuntimeException("User was not found ");
        }else{
            return userMapper.UserDetailsToUserDetailsDTO(details.get());
        }
    }

    public UserDetailsDTO addUser(UserDetailsDTO newUser){
        UserDetails newUserDetails = userMapper.userDetailsDTOToUserDetails(newUser);
        UserDetails savedUser = userDetailsRepo.save(newUserDetails);
        log.info("User added successfully {}",savedUser);

        return userMapper.UserDetailsToUserDetailsDTO(savedUser);
    }

}
