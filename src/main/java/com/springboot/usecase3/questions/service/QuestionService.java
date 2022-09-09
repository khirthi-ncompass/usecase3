package com.springboot.usecase3.questions.service;

import com.springboot.usecase3.questions.dto.AddQuestionDto;
import com.springboot.usecase3.questions.dto.AnswersToQuestionIdDto;
import com.springboot.usecase3.questions.dto.DisplayAllQuestionsDto;
import com.springboot.usecase3.users.dto.AnswersByUserIdDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    AddQuestionDto createQuestion (AddQuestionDto newUser) throws Exception;

    List<AnswersToQuestionIdDto> answersToQuestionIdDto(String questionId);

    List<DisplayAllQuestionsDto> questionsInAscendingOrderOnTime();

    List<DisplayAllQuestionsDto> questionsInDescendingOrderOnTime();
}
