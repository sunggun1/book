package com.example.demo.global.customException;

import com.example.demo.global.errorcode.BookErrorCode;

public class BookCustomException extends CustomException {

    public static final BookCustomException NOT_FOUND_BOOK =
            new BookCustomException(BookErrorCode.NOT_FOUND_BOOK);

    public BookCustomException(BookErrorCode bookErrorCode) {
        super(bookErrorCode);
    }
}
