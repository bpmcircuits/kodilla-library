package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "book_copies")
public class BookCopies {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "title_id")
    private Long titleId;

    @Column(name = "status")
    private BookStatus status;

}
