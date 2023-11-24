package com.example.demo.service;

import com.example.demo.dto.book.request.BookCreateRequestDto;
import com.example.demo.dto.book.request.BookUpdateRequestDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.BookImage;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.User;
import com.example.demo.global.customException.*;
import com.example.demo.global.errorcode.CommonErrorCode;
import com.example.demo.repository.BookImageRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookImageService bookImageService;
    private final BookImageRepository bookImageRepository;
    private final FileService fileService;
    private final UserRepository userRepository;
    private final BorrowService borrowService;
    private final BorrowRepository borrowRepository;

    public void create(String userId, BookCreateRequestDto bookCreateRequestDto, MultipartFile bookImageFile) throws Exception{
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);

        Book book = Book.builder()
                .name(bookCreateRequestDto.getName())
                .bookUseYn(false)
                .user(user)
                .build();

        Book savedBook = bookRepository.save(book);
        bookImageService.saveImage(savedBook, bookImageFile);
    }

    public void update(Long bookId, BookUpdateRequestDto bookUpdateRequestDto, MultipartFile bookImageFile) throws Exception{
        Book book = bookRepository.findById(bookId).orElseThrow(() -> BookCustomException.NOT_FOUND_BOOK);
        book.updateBook(bookUpdateRequestDto);
        bookImageService.updateImage(book.getId(), bookImageFile);
    }

    public void borrowBook(String userId, Long bookId) throws Exception{
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> BookCustomException.NOT_FOUND_BOOK);
        if(book.isBookUseYn() == false){
            book.borrowBook();
            borrowService.create(book, user);
        }else{
            throw BorrowCustomException.ALREADY_BORROWED;
        }
    }

    public void returnBook(String userId, Long bookId) throws Exception{
        User user = userRepository.findById(Long.parseLong(userId)).orElseThrow(() -> UserCustomException.NOT_FOUND_USER);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> BookCustomException.NOT_FOUND_BOOK);

        if(book.isBookUseYn() == true){
            System.out.println(book.toString());
            book.returnBook();
            Borrow borrow = borrowRepository.findFirstByBookAndUserOrderByIdDesc(book,user).orElseThrow(() -> BorrowCustomException.NOT_FOUND_BORROW);
            borrow.updateReturnDate();
        }else{
            throw BorrowCustomException.NOT_BORROWED;
        }
    }
}
