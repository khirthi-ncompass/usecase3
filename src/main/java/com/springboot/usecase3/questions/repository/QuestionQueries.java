package com.springboot.usecase3.questions.repository;

public class QuestionQueries {

    public static final String ADD_QUESTION = "INSERT INTO QUESTIONS (questionId, userId, question, date) VALUES (?,?,?,?)";

    /*TO GET ALL ANSWERS TO A QUESTION ID*/
    public static final String ANSWERS_TO_QUESTION_ID ="select q.questionId, q.date as questionDate, q.question, a.answerId, a.date as answerDate, a.answer from questions as q inner join answers as a on (q.questionId=a.questionId) where q.questionId = ?" ;

    /*DISPLAY ALL QUESTIONS WITH SORTING ACCORDING TO TIME (DESC)*/
    public static final String DISPLAY_QUESTIONS_DESCENDING_ORDER =  "SELECT * FROM QUESTIONS order by date desc;";

    /*DISPLAY ALL QUESTIONS WITH SORTING ACCORDING TO TIME (ASC)*/
    public static final String DISPLAY_QUESTIONS_ASCENDING_ORDER =  "SELECT * FROM QUESTIONS order by date asc;";
}
