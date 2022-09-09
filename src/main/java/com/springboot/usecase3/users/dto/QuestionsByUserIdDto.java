package com.springboot.usecase3.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionsByUserIdDto {
    private String userId;
    private String questionId;
    private String date;
    private String question;
}
