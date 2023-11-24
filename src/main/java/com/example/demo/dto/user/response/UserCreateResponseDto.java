package com.example.demo.dto.user.response;

import com.example.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCreateResponseDto {
    private Long id;
    private String email;

    public static UserCreateResponseDto of(User user) {
        return UserCreateResponseDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
