package com.example.demo.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCreateRequestDto {
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;
    @NotBlank(message = "패스워드는 필수입니다.")
    private String password;
    @NotBlank(message = "패스워드 재입력은 필수입니다.")
    private String passwordCheck;
}
