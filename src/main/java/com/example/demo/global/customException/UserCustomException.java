package com.example.demo.global.customException;

import com.example.demo.global.errorcode.BookErrorCode;
import com.example.demo.global.errorcode.UserErrorCode;

public class UserCustomException extends CustomException {

    public static final UserCustomException NOT_FOUND_USER =
            new UserCustomException(UserErrorCode.NOT_FOUND_USER);

    public static final UserCustomException NOT_FOUND_PASSWORD =
            new UserCustomException(UserErrorCode.NOT_FOUND_PASSWORD);

    public static final UserCustomException ALEADY_EMAIL_EXIST =
            new UserCustomException(UserErrorCode.ALEADY_EMAIL_EXIST);

    public static final UserCustomException NOT_MATCH_PASSWORD =
            new UserCustomException(UserErrorCode.NOT_MATCH_PASSWORD);

    public UserCustomException(UserErrorCode userErrorCode) {
        super(userErrorCode);
    }
}
