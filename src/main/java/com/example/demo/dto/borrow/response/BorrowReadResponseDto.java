package com.example.demo.dto.borrow.response;

import com.example.demo.dto.book.response.BookBorrowResponseDto;
import com.example.demo.dto.user.response.UserBorrowResponseDto;
import com.example.demo.entity.Borrow;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BorrowReadResponseDto {
    private Long borrowId;
    private BookBorrowResponseDto book;
    private UserBorrowResponseDto user;

    public static BorrowReadResponseDto of(Borrow borrow, BookBorrowResponseDto book, UserBorrowResponseDto user) {
        return BorrowReadResponseDto.builder()
                .borrowId(borrow.getId())
                .book(book)
                .user(user)
                .build();
    }
}
