package com.example.quotesapi;

import com.example.quotesapi.Domains.quotes;
import com.example.quotesapi.Domains.searchTag;
import com.example.quotesapi.Domains.userDetails;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class QuotesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotesApiApplication.class, args);
	}



}
