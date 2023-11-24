package com.example.demo.global.errorcode;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum UserErrorCode implements ErrorCode {
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다."),
    NOT_FOUND_PASSWORD(HttpStatus.NOT_FOUND, "패스워드를 입력해주세요"),
    ALEADY_EMAIL_EXIST(HttpStatus.NOT_FOUND, "이메일이 이미 존재합니다."),

    NOT_MATCH_PASSWORD(HttpStatus.NOT_FOUND, "패스워드가 맞지 않습니다.");

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
