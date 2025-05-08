package com.kodilla.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "BookCopy.updateStatusById",
                query = "UPDATE BookCopy bc SET bc.status = :status WHERE bc.id = :id"),
        @NamedQuery(name = "BookCopy.getAmountOfCopiesById",
                query = "SELECT COUNT(*) FROM BookCopy bc WHERE bc.book.id = :id AND bc.status = 'AVAILABLE'")
})


@Getter
@NoArgsConstructor
@Entity
@Table(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @OneToMany(mappedBy = "bookCopy", cascade = CascadeType.ALL)
    private List<RentBook> rentBooks = new ArrayList<>();

    public BookCopy(Long id, Book book, BookStatus status) {
        this.id = id;
        this.book = book;
        this.status = status;
    }
}
