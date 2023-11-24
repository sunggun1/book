package com.example.demo.global.errorcode;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum BorrowErrorCode implements ErrorCode {
    NOT_FOUND_BORROW(HttpStatus.NOT_FOUND, "해당 이력이 존재하지 않습니다."),
    ALREADY_BORROWED(HttpStatus.NOT_FOUND, "책이 대여중입니다."),
    NOT_BORROWED(HttpStatus.NOT_FOUND, "책이 이미 반환되었습니다.");

    private HttpStatus status;
    private String message;

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

