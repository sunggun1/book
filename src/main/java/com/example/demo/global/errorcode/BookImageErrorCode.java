package com.example.demo.global.errorcode;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum BookImageErrorCode implements ErrorCode {
    ERORR_FROM_GET_IMAGE(HttpStatus.NOT_FOUND, "이미지를 가져오는데 에러가 발생하였습니다."),
    ERORR_FROM_UPDATE_IMAGE(HttpStatus.NOT_FOUND, "이미지를 수정하는데 에러가 발생하였습니다."),
    NOT_FOUND_BOOK_IMAGE(HttpStatus.NOT_FOUND, "책 이미지가 없습니다.");

    
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