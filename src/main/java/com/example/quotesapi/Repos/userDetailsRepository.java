package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.userDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userDetailsRepository extends JpaRepository<userDetails,Long> {

}
