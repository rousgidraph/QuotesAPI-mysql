package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userDetailsRepository extends JpaRepository<UserDetails,Long> {
    boolean existsByUserIdAllIgnoreCase(Long userId);

}
