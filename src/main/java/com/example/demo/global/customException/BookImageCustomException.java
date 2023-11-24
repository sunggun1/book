package com.example.demo.global.customException;

import com.example.demo.global.errorcode.BookErrorCode;
import com.example.demo.global.errorcode.BookImageErrorCode;

public class BookImageCustomException extends CustomException {

    public static final BookImageCustomException ERORR_FROM_GET_IMAGE =
            new BookImageCustomException(BookImageErrorCode.ERORR_FROM_GET_IMAGE);

    public static final BookImageCustomException NOT_FOUND_BOOK_IMAGE =
            new BookImageCustomException(BookImageErrorCode.NOT_FOUND_BOOK_IMAGE);


    public BookImageCustomException(BookImageErrorCode bookImageErrorCode) {
        super(bookImageErrorCode);
    }
}

