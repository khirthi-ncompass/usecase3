package com.springboot.usecase3.questions.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Question {

    private String questionId;

    private String userId;

    private String question;

    private String date;
}
