package com.springboot.usecase3.users.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


public class UserQueries {

    public static final String CREATE_ACCOUNT = ("INSERT INTO USERS (userId, emailId, password) VALUES (?,?,?)");


    /*TO GET ALL QUESTIONS ASKED BY USER ID*/
    public static final String QUESTIONS_ASKED_BY_USER_ID = ("select u.userId, q.questionId, q.date as questionDate, q.question from users as u" +
            "             inner join questions as q on (u.userId=q.userId) where u.userId = ?");


    /*TO GET ALL ANSWERS Answered BY USER ID*/
    public static final String ANSWERS_ANSWERED_BY_USER_ID = ("select u.userId, a.answerId, a.date as answerDate, a.answer from users as u inner join answers as a on (u.userId=a.userId) where u.userId = ?");

    //    /*displays all the questions' user asked and all the answers' user answered */
//    private static final String DISPLAY_EVERYTHING_RELATED_TO_USER = ("select u.userId, q.questionId, q.date as questionDate, q.question, a.answerId, a.date as answerDate, a.answer from users as u" +
//            "inner join answers as a on (u.userId = a.userId)" +
//            "inner join questions as q on (a.userId=q.userId) where u.userId = ?"),
}
