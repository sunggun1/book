package com.example.demo.controller;

import com.example.demo.dto.book.request.BookCreateRequestDto;
import com.example.demo.dto.book.request.BookUpdateRequestDto;
import com.example.demo.dto.common.ResponseDto;
import com.example.demo.global.customException.CommonCustomException;
import com.example.demo.service.BookService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping("")
    public ResponseEntity<ResponseDto<?>> create(@AuthenticationPrincipal String userId, @Valid BookCreateRequestDto bookCreateRequestDto, @RequestParam("bookImageFile") MultipartFile bookImageFile, HttpServletRequest req ) throws Exception {
        try{
            if(bookImageFile.isEmpty()) throw new Exception("bookImageFile을 넣어주세요.");

            bookService.create(userId, bookCreateRequestDto, bookImageFile);
            return new ResponseEntity<>(new ResponseDto<>(true, "success", null), HttpStatus.OK);
        }catch (Exception exception){
            throw exception;
        }
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<ResponseDto<?>> update(@PathVariable("bookId") Long bookId, @Valid BookUpdateRequestDto bookUpdateRequestDto, @RequestParam("bookImageFile") MultipartFile bookImageFile) throws Exception{
        try{
            if(bookImageFile.isEmpty()) throw new Exception("bookImageFile을 넣어주세요.");

            bookService.update(bookId, bookUpdateRequestDto, bookImageFile);
            return new ResponseEntity<>(new ResponseDto<>(true, "success", null), HttpStatus.OK);
        }catch (Exception exception){
            throw exception;
        }
    }

    @PostMapping("/{bookId}/borrow")
    public ResponseEntity<ResponseDto<?>> borrowBook(@AuthenticationPrincipal String userId, @PathVariable("bookId") Long bookId) throws Exception{
        try{
            bookService.borrowBook(userId, bookId);
            return new ResponseEntity<>(new ResponseDto<>(true, "success", null), HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<ResponseDto<?>> returnBook(@AuthenticationPrincipal String userId, @PathVariable("bookId") Long bookId) throws Exception{
        try{
            bookService.returnBook(userId, bookId);
            return new ResponseEntity<>(new ResponseDto<>(true, "success", null), HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }
}
