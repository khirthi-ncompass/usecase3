package com.springboot.usecase3.questions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddQuestionDto {
    private String userId;
    private String question;
    private String questionId;
    private String date;
}
