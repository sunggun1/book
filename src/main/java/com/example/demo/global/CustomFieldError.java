package com.example.demo.global;

import com.example.demo.global.errorcode.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class CustomFieldError {

    private String field;
    private String invalidValue;
    private String reason;

    public CustomFieldError(FieldError fieldError) {
        this.field = fieldError.getField();
        this.invalidValue = fieldError.getRejectedValue().toString();
        this.reason = fieldError.getDefaultMessage();
    }
}