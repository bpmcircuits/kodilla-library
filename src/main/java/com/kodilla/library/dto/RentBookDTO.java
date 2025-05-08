package com.kodilla.library.dto;

public record RentBookDTO(Long id,
                          Long bookCopyId,
                          Long userId,
                          String rentDate,
                          String returnDate) {
}
