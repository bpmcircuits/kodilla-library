package com.kodilla.library.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "rent")
public class RentBook {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rent_date")
    private LocalDate rentDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
}
