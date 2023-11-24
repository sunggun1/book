package com.example.demo.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignInRequestDto {
    private String email;
    private String password;
}
