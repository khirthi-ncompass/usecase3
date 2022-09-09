package com.springboot.usecase3.questions.controller;

import com.springboot.usecase3.questions.dto.AddQuestionDto;
import com.springboot.usecase3.questions.entity.request.AddQuestionRequest;
import com.springboot.usecase3.questions.entity.response.AddQuestionResponse;
import com.springboot.usecase3.questions.exceptions.QuestionErrorMessages;
import com.springboot.usecase3.questions.service.QuestionService;
import com.springboot.usecase3.users.exceptions.UserErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @PostMapping("add-question")
    ResponseEntity<AddQuestionResponse> addQuestion(@Valid @RequestBody AddQuestionRequest newUser) throws Exception {

        if(newUser.getUserId().isEmpty()) throw new Exception(QuestionErrorMessages.MISSING_USER_ID.getErrorMessage());
        if(newUser.getQuestion().isEmpty()) throw new Exception(QuestionErrorMessages.MISSING_QUESTION.getErrorMessage());

        AddQuestionDto userDto = new AddQuestionDto();
        BeanUtils.copyProperties(newUser, userDto);

        AddQuestionDto addedQuestion = this.questionService.createQuestion(userDto);

        AddQuestionResponse addQuestionResponse = new AddQuestionResponse();
        addQuestionResponse.setUserId(addedQuestion.getUserId());
        addQuestionResponse.setQuestion(addedQuestion.getQuestion());
        addQuestionResponse.setQuestionId(addedQuestion.getQuestionId());
        addQuestionResponse.setDate(addedQuestion.getDate());

        return new ResponseEntity<>(addQuestionResponse, HttpStatus.OK);
    }

    @GetMapping("showAnswers/{questionId}")
    ResponseEntity<List> getQuestionsByUserId (@PathVariable String questionId) throws Exception {

        List info = questionService.answersToQuestionIdDto(questionId);
        if (info == null) throw new Exception(QuestionErrorMessages.QUESTION_ID_DOESNT_EXIST.getErrorMessage());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("showAllQuestions/ascending")
    ResponseEntity<List> displayAllQuestionsInAscendingOrder () throws Exception {

        List info = questionService.questionsInAscendingOrderOnTime();
        if (info == null) throw new Exception(QuestionErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("showAllQuestions/descending")
    ResponseEntity<List> displayAllQuestionsInDescendingOrder () throws Exception {

        List info = questionService.questionsInDescendingOrderOnTime();
        if (info == null) throw new Exception(QuestionErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
