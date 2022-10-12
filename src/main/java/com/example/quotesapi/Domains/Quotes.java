package com.example.quotesapi.Domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Quotes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quoteId;
    private String quoteStatement;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tagMap",
            joinColumns = @JoinColumn(name="quoteId"),
            inverseJoinColumns = @JoinColumn(name = "tagId"))
    @JsonManagedReference
    Set<SearchTag> quoteTags=new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "verifyMap",
            joinColumns = @JoinColumn(name="quoteId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    @JsonManagedReference
    private Set<UserDetails> verifiers=new HashSet<>();
    @ManyToOne()
    @JoinColumn(name = "userId",nullable = false)
    private UserDetails submittedBy;
    @Column(name="dateSubmitted", updatable = false,nullable = false)
    @CreationTimestamp
    private Date dateSubmitted;

    @Override
    public String toString() {
        return "quotes{" +
                "quoteId=" + quoteId +
                ", quoteStatement='" + quoteStatement + '\'' +
                ", quoteTags=" + this.getQuoteTags() +
                ", verifiers=" + this.getVerifiers() +
                ", submittedBy=" + submittedBy +
                ", dateSubmitted=" + dateSubmitted +
                '}';
    }
}
