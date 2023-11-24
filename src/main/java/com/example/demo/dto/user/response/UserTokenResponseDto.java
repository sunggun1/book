package com.example.demo.dto.user.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserTokenResponseDto {
    private String token;
    private Long userId;
    private String email;

    public static UserTokenResponseDto of(String token, Long userId, String email) {
        return UserTokenResponseDto.builder()
                .token(token)
                .userId(userId)
                .email(email)
                .build();
    }
}
