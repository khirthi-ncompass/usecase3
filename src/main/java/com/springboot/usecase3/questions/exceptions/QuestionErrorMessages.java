package com.springboot.usecase3.questions.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionErrorMessages {

    MISSING_USER_ID("Missing userId. Please enter userId."),
    MISSING_QUESTION("Missing Question. Please enter a question."),

    QUESTION_ID_DOESNT_EXIST("Given questionId doesnt exist. Please enter valid questionId."),

    NO_RECORD_FOUND("No record found.");

    private String errorMessage;
}
