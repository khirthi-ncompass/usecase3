package com.springboot.usecase3.questions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswersToQuestionIdDto {
    private String questionId;
    private String questionDate;
    private String question;

    private String answerId;
    private String answerDate;
    private String answer;

}
