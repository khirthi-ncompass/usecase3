package com.springboot.usecase3.answers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnswerErrorMessages {

    MISSING_USER_ID("Missing userId. Please enter userId."),
    MISSING_QUESTION_ID("Missing questionId. Please enter a questionId."),
    ENTER_VALID_UPVOTE_OR_DOWN_VOTE("Please enter UP_VOTE OR DOWN_VOTE"),
    MISSING_ANSWER("Missing answer. Please enter an answer.");

    private String errorMessage;
}
