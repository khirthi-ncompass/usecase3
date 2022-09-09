package com.springboot.usecase3.questions.entity.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AddQuestionResponse {
    private String questionId;
    private String userId;
    private String question;
    private String date;
}
