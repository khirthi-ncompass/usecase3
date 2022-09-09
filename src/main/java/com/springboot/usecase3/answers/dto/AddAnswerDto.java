package com.springboot.usecase3.answers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAnswerDto {
    private String answerId;
    private String userId;
    private String questionId;
    private String answer;
    private String date;

    //answerId, userId, questionId, answer, date, votes
}
