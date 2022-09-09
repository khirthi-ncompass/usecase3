package com.springboot.usecase3.questions.service;

import com.springboot.usecase3.questions.dto.AddQuestionDto;
import com.springboot.usecase3.questions.dto.AnswersToQuestionIdDto;
import com.springboot.usecase3.questions.dto.DisplayAllQuestionsDto;
import com.springboot.usecase3.questions.entity.Question;
import com.springboot.usecase3.questions.exceptions.QuestionErrorMessages;
import com.springboot.usecase3.questions.repository.QuestionRepository;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public AddQuestionDto createQuestion(AddQuestionDto newQuestion) throws Exception {

        Question questionEntity = new Question();
        BeanUtils.copyProperties(newQuestion, questionEntity);

        String questionId = QuestionUtils.generateQuestionId(6);
        questionEntity.setQuestionId("QUES" + questionId);

        String date = QuestionUtils.generateDate();
        questionEntity.setDate(date);

        this.questionRepository.createQuestion(questionEntity);

        AddQuestionDto returnedValue = new AddQuestionDto();
        BeanUtils.copyProperties(questionEntity, returnedValue);

        return returnedValue;
    }

    @Override
    public List<AnswersToQuestionIdDto> answersToQuestionIdDto(String questionId) {
        try {
            List returnValue = questionRepository.answersToQuestionId(questionId);
            if(returnValue.isEmpty())
            {
                throw new Exception(QuestionErrorMessages.QUESTION_ID_DOESNT_EXIST.getErrorMessage());
            } else {
                return returnValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DisplayAllQuestionsDto> questionsInAscendingOrderOnTime() {
        return this.questionRepository.questionsInAscendingOrderOnTime();
    }

    @Override
    public List<DisplayAllQuestionsDto> questionsInDescendingOrderOnTime() {
        return this.questionRepository.questionsInDescendingOrderOnTime();
    }
}
