package com.example.demo.dto.book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class BookCreateRequestDto {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
}
