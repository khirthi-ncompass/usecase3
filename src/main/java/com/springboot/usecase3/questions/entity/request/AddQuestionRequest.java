package com.springboot.usecase3.questions.entity.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddQuestionRequest {

    @NotNull(message = "userId cannot be null")
    private String userId;

    @NotNull(message = "question cannot be null")
    private String question;

}
