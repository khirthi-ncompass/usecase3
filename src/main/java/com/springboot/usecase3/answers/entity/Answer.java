package com.springboot.usecase3.answers.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
public class Answer {

    private String answerId;

    private String userId;

    private String questionId;

    private String answer;

    private String date;

    private int upVote;

    private int downVote;

    //answerId, userId, questionId, answer, date, votes
}
