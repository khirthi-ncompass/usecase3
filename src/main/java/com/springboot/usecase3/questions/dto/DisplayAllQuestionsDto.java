package com.springboot.usecase3.questions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayAllQuestionsDto {
    private String questionId;
    private String userId;
    private String question;
    private String date;
}
