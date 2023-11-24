package com.example.demo.controller;

import com.example.demo.dto.borrow.response.BorrowReadResponseDto;
import com.example.demo.dto.common.ResponseDto;
import com.example.demo.entity.Borrow;
import com.example.demo.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping("/api/borrow/book/{bookId}")
    public ResponseEntity<ResponseDto<List<BorrowReadResponseDto>>> get(@AuthenticationPrincipal String userId, @PathVariable("bookId") Long bookId){
        try{
            List<Borrow> borrows = borrowService.getBookHistory(bookId);
            List<BorrowReadResponseDto> result = borrowService.getBookHistoryDto(borrows, Long.parseLong(userId));
            return new ResponseEntity<>(new ResponseDto<>(true, "success", result), HttpStatus.OK);
        }catch (Exception ex){
            throw ex;
        }
    }
}
