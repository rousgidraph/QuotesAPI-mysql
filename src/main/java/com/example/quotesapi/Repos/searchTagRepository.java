package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.SearchTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface searchTagRepository extends JpaRepository<SearchTag,Long> {
    @Query("select s from SearchTag s where upper(s.tag) = upper(?1)")
    Optional<SearchTag>  findByTagIgnoreCase(String tag);

    boolean existsByTagId(Long tagId);



}
