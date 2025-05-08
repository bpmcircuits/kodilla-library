package com.kodilla.library.mapper;

import com.kodilla.library.domain.BookCopy;
import com.kodilla.library.domain.RentBook;
import com.kodilla.library.domain.User;
import com.kodilla.library.dto.RentBookDTO;
import com.kodilla.library.repository.BookCopyRepository;
import com.kodilla.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentMapper {

    private final BookCopyRepository bookCopyRepository;
    private final UserRepository userRepository;

    public RentBookDTO mapToRentDTO(RentBook rentBook) {
        Long id = rentBook.getId();
        Long bookCopyId = rentBook.getBookCopy().getId();
        Long userId = rentBook.getUser().getId();
        String rentDate = rentBook.getRentDate() == null ?
                null : rentBook.getRentDate().toString();
        String returnDate = rentBook.getReturnDate() == null ?
                null : rentBook.getReturnDate().toString();
        return new RentBookDTO(id, bookCopyId, userId, rentDate, returnDate);
    }

    public RentBook mapToRent(RentBookDTO rentBookDTO) {
        Long id = rentBookDTO.id();

        BookCopy bookCopy = bookCopyRepository.findById(rentBookDTO.bookCopyId())
                .orElseThrow(() -> new IllegalArgumentException("BookCopy not found with ID: " + rentBookDTO.bookCopyId()));

        User user = userRepository.findById(rentBookDTO.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + rentBookDTO.userId()));

        LocalDate rentDate = rentBookDTO.rentDate() == null
                ? null : LocalDate.parse(rentBookDTO.rentDate());

        LocalDate returnDate = rentBookDTO.returnDate() == null
                ? null : LocalDate.parse(rentBookDTO.returnDate());

        return new RentBook(id, bookCopy, user, rentDate, returnDate);
    }

    public List<RentBookDTO> mapToRentDTOList(List<RentBook> rentBooks) {
        return rentBooks.stream().map(this::mapToRentDTO).toList();
    }
}
