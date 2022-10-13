package com.example.quotesapi.Domains;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String secondName;
    private String email;

    @Column(name="dateJoined", updatable = false,nullable = false)
    @CreationTimestamp
    private Date dateJoined;

    @OneToMany(mappedBy = "submittedBy",fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Quotes> submittedQuotes = new HashSet<>();

    @ManyToMany (mappedBy="verifiers")
    @JsonBackReference
    private Set<Quotes> verifiedQuotes = new HashSet<>();

    @Override
    public String toString() {
        return "userDetails{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", dateJoined=" + dateJoined +'\'' +
                ",email=" +email+'\'' +
                '}';
    }
}
