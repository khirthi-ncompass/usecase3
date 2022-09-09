package com.springboot.usecase3.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswersByUserIdDto {
    private String userId;
    private String answerId;
    private String date;
    private String answer;
}
