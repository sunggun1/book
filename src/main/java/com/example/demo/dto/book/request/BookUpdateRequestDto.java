package com.example.demo.dto.book.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookUpdateRequestDto {
    @NotBlank(message = "이름은 필수 입니다.")
    private String name;
    @NotNull(message = "사용가능 여부는 필수 입니다.")
    private Boolean useYn;
}