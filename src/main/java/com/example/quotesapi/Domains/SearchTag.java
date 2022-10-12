package com.example.quotesapi.Domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;
    private String tag;
    @ManyToMany(mappedBy = "quoteTags",fetch = FetchType.LAZY)
    @JsonBackReference
    Set<Quotes> qoutes;


    @Override
    public String toString() {
        return tag;
    }
}
