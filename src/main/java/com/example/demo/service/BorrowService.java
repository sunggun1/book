package com.example.demo.service;

import com.example.demo.dto.book.response.BookBorrowResponseDto;
import com.example.demo.dto.borrow.response.BorrowReadResponseDto;
import com.example.demo.dto.user.response.UserBorrowResponseDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.User;
import com.example.demo.global.customException.BookCustomException;
import com.example.demo.global.customException.BorrowCustomException;
import com.example.demo.global.customException.UserCustomException;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public void create(Book book, User user){
        Borrow borrow = Borrow.builder()
                .book(book)
                .user(user)
                .borrowDate(LocalDateTime.now())
                .returnDate(null)
                .build();

        borrowRepository.save(borrow);
    }

    public List<Borrow> getBookHistory(Long bookId){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> BookCustomException.NOT_FOUND_BOOK);
        List<Borrow> borrowsByBook = borrowRepository.findBorrowsByBook(book).orElseThrow(() -> BorrowCustomException.NOT_FOUND_BORROW);
        return borrowsByBook;
    }

    public List<BorrowReadResponseDto> getBookHistoryDto(List<Borrow> borrows, Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        List<BorrowReadResponseDto> borrowReadResponseDtos = new ArrayList<>();
        for (Borrow borrow : borrows) {
            BookBorrowResponseDto bookBorrowResponseDto = BookBorrowResponseDto.of(borrow.getBook());
            UserBorrowResponseDto userBorrowResponseDto = UserBorrowResponseDto.of(user);
            BorrowReadResponseDto borrowReadResponseDto = BorrowReadResponseDto.of(borrow, bookBorrowResponseDto, userBorrowResponseDto);

            borrowReadResponseDtos.add(borrowReadResponseDto);
        }
        return borrowReadResponseDtos;
    }
}
