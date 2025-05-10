package com.kodilla.library.domain;

import lombok.*;

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
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book_copy")
public class BookCopy {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus status;

    @OneToMany(mappedBy = "bookCopy", cascade = CascadeType.ALL)
    private final List<RentBook> rentBooks = new ArrayList<>();
}
