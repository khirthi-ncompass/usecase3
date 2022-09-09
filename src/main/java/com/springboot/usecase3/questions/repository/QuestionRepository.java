package com.springboot.usecase3.questions.repository;


import com.springboot.usecase3.questions.dto.AnswersToQuestionIdDto;
import com.springboot.usecase3.questions.dto.DisplayAllQuestionsDto;
import com.springboot.usecase3.questions.entity.Question;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository {
    Question createQuestion(Question newQuestion);

    List<AnswersToQuestionIdDto> answersToQuestionId(String questionId);

    List<DisplayAllQuestionsDto> questionsInAscendingOrderOnTime();

    List<DisplayAllQuestionsDto> questionsInDescendingOrderOnTime();
}
