package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface quotesRepository extends JpaRepository<quotes,Long> {
}
