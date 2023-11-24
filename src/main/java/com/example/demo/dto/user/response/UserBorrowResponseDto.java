package com.example.demo.dto.user.response;

import com.example.demo.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserBorrowResponseDto {
    private Long userId;
    private String email;

    public static UserBorrowResponseDto of(User user) {
        return UserBorrowResponseDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .build();
    }
}
