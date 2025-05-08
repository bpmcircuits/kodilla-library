package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "account_created")
    private LocalDate accountCreated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<RentBook> rentBooks = new ArrayList<>();

    public User(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.accountCreated = LocalDate.now();
    }
}
