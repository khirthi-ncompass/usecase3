package com.springboot.usecase3.answers.entity.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddAnswerRequest {

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "questionId cannot be null")
    private String questionId;

    @NotNull(message = "answer cannot be null")
    private String answer;
}
