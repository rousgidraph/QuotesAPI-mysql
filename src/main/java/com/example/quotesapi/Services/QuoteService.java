package com.example.quotesapi.Services;

import com.example.quotesapi.Domains.Quotes;
import com.example.quotesapi.Domains.SearchTag;
import com.example.quotesapi.Domains.UserDetails;
import com.example.quotesapi.Repos.quotesRepository;
import com.example.quotesapi.Repos.searchTagRepository;
import com.example.quotesapi.Repos.userDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j

public class QuoteService {

    @Autowired
    quotesRepository quotesRepo;

    @Autowired
    searchTagRepository searchTagRepo;

    @Autowired
    userDetailsRepository userDetailsRepo;



    void startUp() throws JsonProcessingException {
        log.info("Starting up");
        UserDetails user1 = new UserDetails();
        UserDetails user2 = new UserDetails();
        UserDetails user3 = new UserDetails();
        UserDetails user4 = new UserDetails();

        user1.setFirstName("John");
        user1.setSecondName("Lenon");
        user1.setEmail("lenon@mail.com");

        user2.setFirstName("Marquasha");
        user2.setSecondName("Felipa");
        user2.setEmail("demarius_christofferso2bq@fork.ez");

        user3.setFirstName("Clarance");
        user3.setSecondName("Tahesha");
        user3.setEmail("marquin_fletcherqu3@bizarre.hry");

        user4.setFirstName("Mohamed");
        user4.setSecondName("Lavona");
        user4.setEmail("kaytlin_arguellorwh@designing.ag");

        //save the users
        userDetailsRepo.saveAll(List.of(user1,user2,user3,user4));

        SearchTag tag1 = new SearchTag();
        SearchTag tag2 = new SearchTag();

        tag1.setTag("Enviromental");
        tag2.setTag("Tech");


        //save the tags
        searchTagRepo.saveAll(List.of(tag1,tag2));

        Quotes quotes1 = new Quotes();
        Quotes quotes2 = new Quotes();
        Quotes quotes3 = new Quotes();
        Quotes quotes4 = new Quotes();

        quotes1.setQuoteStatement("Graduation interesting founder appliances utilization expenditure albania, women representative standings upon infrastructure sao volunteers, proof personals guaranteed moss geneva denver emerging, survive. ");
        quotes1.setVerifiers(Set.of(user1,user3));
        quotes1.setSubmittedBy(user4);
        quotes1.setQuoteTags(Set.of(tag1));

        quotes2.setQuoteStatement("Nav consoles cruz analyzes. ");
        quotes2.setVerifiers(Set.of(user2,user4));
        quotes2.setSubmittedBy(user1);
        quotes1.setQuoteTags(Set.of(tag2));

        quotes3.setQuoteStatement("Ebooks avenue hosted endif affecting library food, above course obvious counters thanksgiving expertise intermediate, homework computers translation villa survivor same loops, seconds she arrived ebony operations colon. ");
        quotes3.setSubmittedBy(user4);


        quotes4.setQuoteStatement("Thought patch upgrading riding mistakes. ");
        quotes4.setVerifiers(Set.of(user3));
        quotes4.setSubmittedBy(user1);
        quotes1.setQuoteTags(Set.of(tag1));


        //save the quotes
        quotesRepo.save(quotes3);
        quotesRepo.saveAll(List.of(quotes1,quotes2,quotes4));


        Optional<Quotes> byId = quotesRepo.findById(2L);
        Hibernate.initialize(byId.get());
        log.info("Value returned {}",byId.get().toString());
        ObjectMapper mapper = new ObjectMapper();
        String asString = mapper.writeValueAsString(byId.get());
        log.info("Json {}",asString);

        Optional<UserDetails> repoById = userDetailsRepo.findById(1L);
        log.info("User details : {}",mapper.writeValueAsString(repoById.get().getSubmittedQuotes()));


        Optional<SearchTag> searchTag = searchTagRepo.findById(1L);
        SearchTag proxy = searchTag.get();
        Hibernate.initialize(proxy);
        log.info("Search tag info : {}",mapper.writeValueAsString(proxy));


        log.info("... clossing ");
    }

}
