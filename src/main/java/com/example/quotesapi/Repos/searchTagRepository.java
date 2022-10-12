package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.SearchTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface searchTagRepository extends JpaRepository<SearchTag,Long> {
}
