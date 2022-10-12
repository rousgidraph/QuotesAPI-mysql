package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.searchTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository

public interface searchTagRepository extends JpaRepository<searchTag,Long> {
}
