package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public BookCopy(Book book, BookStatus status) {
        this.book = book;
        this.status = status;
    }

}
