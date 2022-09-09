package com.springboot.usecase3.users.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorMessages {

    MISSING_USER_ID("Missing userId. Please enter userId."),
    MISSING_EMAIL_ID("Missing emailId. Please enter emailId."),
    MISSING_PASSWORD("Missing password. Please enter password."),
    USER_ID_DOESNT_EXIST("Given user id doesn't exist. Please  enter correct UserId");


    private String errorMessage;
}
