package com.example.demo.dto.book.response;

import com.example.demo.entity.Book;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookBorrowResponseDto {
    private Long id;
    private String name;
    private boolean bookUseYn;

    public static BookBorrowResponseDto of(Book book) {
        return BookBorrowResponseDto.builder()
                .id(book.getId())
                .name(book.getName())
                .bookUseYn(book.isBookUseYn())
                .build();
    }
}
