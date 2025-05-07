package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

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

    public User(String name, String surname, LocalDate accountCreated) {
        this.name = name;
        this.surname = surname;
        this.accountCreated = accountCreated;
    }
}
