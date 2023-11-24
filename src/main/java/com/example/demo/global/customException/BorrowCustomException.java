package com.example.demo.global.customException;

import com.example.demo.global.errorcode.BookErrorCode;
import com.example.demo.global.errorcode.BorrowErrorCode;

public class BorrowCustomException extends CustomException {

    public static final BorrowCustomException NOT_FOUND_BORROW =
            new BorrowCustomException(BorrowErrorCode.NOT_FOUND_BORROW);

    public static final BorrowCustomException ALREADY_BORROWED =
            new BorrowCustomException(BorrowErrorCode.ALREADY_BORROWED);

    public static final BorrowCustomException NOT_BORROWED =
            new BorrowCustomException(BorrowErrorCode.NOT_BORROWED);


    public BorrowCustomException(BorrowErrorCode borrowErrorCode) {
        super(borrowErrorCode);
    }
}
