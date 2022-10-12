package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.Quotes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface quotesRepository extends JpaRepository<Quotes,Long> {
    @Query("select q from Quotes q where q.submittedBy.userId = ?1")
    List<Quotes> findBySubmittedBy_UserId(Long userId);

    @Query("select q from Quotes q inner join q.verifiers verifiers where verifiers.userId = ?1")
    List<Quotes> findByVerifiers_UserId(Long userId);

    @Query("select q from Quotes q inner join q.quoteTags quoteTags where quoteTags.tag like %?1%")
    List<Quotes> findByQuoteTags_Tag(String tag);

    @Query("select q from Quotes q inner join q.quoteTags quoteTags where quoteTags.tagId = ?1")
    List<Quotes> findByQuoteTags_TagId(Long tagId);





}
