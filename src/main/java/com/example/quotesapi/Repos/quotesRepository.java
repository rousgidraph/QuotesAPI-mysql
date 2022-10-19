package com.example.quotesapi.Repos;

import com.example.quotesapi.Domains.Quotes;
import com.example.quotesapi.Domains.SearchTag;
import com.example.quotesapi.Domains.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository

public interface quotesRepository extends JpaRepository<Quotes,Long> {
    @Query("select q from Quotes q where q.submittedBy.userId = ?1")
    List<Quotes> findBySubmittedBy_UserId(Long userId);

    @Query("select q from Quotes q inner join q.verifiers verifiers where verifiers.userId = ?1")
    List<Quotes> findByVerifiers_UserId(Long userId);

    List<Quotes> findByQuoteTags_TagIgnoreCase(String tag);

    @Query("select q from Quotes q inner join q.quoteTags quoteTags " +
            "where upper(quoteTags.tag) like upper(concat('%', ?1, '%'))")
    List<Quotes> findByQuoteTags_TagContainsIgnoreCase(String tag);





    @Query("select q from Quotes q inner join q.quoteTags quoteTags where quoteTags.tagId = ?1")
    List<Quotes> findByQuoteTags_TagId(Long tagId);


    @Transactional
    @Modifying
    @Query(value = "insert into verifymap (quoteId,userId)  values(:verifiersId,:quoteId)",nativeQuery = true)
    int updateVerifiersByQuoteId(@Param("verifiersId") Long verifiersId,@Param("quoteId") Long quoteId);



    @Query("select (count(q) > 0) from Quotes q where q.quoteId = ?1")
    boolean existsByQuoteId(Long quoteId);

    @Transactional
    @Modifying
    @Query(value = "insert into tagmap (quoteId,tagId) values(:quoteId,:tagId)",nativeQuery = true)
    int updateQuoteTagsByQuoteId(@Param("tagId") Long tagId, @Param("quoteId")  Long quoteId);

    @Query("select q.submittedBy.userId from Quotes q where q.quoteId = ?1")
    Long findSubmitterByQuoteId(Long quoteId);






}
