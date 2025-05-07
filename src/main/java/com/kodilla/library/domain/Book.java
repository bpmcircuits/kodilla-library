package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCopy> copies = new ArrayList<>();

    public Book(String title, String author, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }
}
