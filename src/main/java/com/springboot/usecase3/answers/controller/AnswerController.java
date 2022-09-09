package com.springboot.usecase3.answers.controller;

import com.springboot.usecase3.answers.dto.AddAnswerDto;
import com.springboot.usecase3.answers.dto.ServiceVotingDto;
import com.springboot.usecase3.answers.dto.VotingDto;
import com.springboot.usecase3.answers.entity.request.AddAnswerRequest;
import com.springboot.usecase3.answers.entity.response.AddAnswerResponse;
import com.springboot.usecase3.answers.exceptions.AnswerErrorMessages;
import com.springboot.usecase3.answers.service.AnswerService;
import com.springboot.usecase3.questions.exceptions.QuestionErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("add-answer")
    ResponseEntity<AddAnswerResponse> addAnswer(@Valid @RequestBody AddAnswerRequest newAnswer) throws Exception {

        if(newAnswer.getUserId().isEmpty()) throw new Exception(AnswerErrorMessages.MISSING_USER_ID.getErrorMessage());
        if(newAnswer.getQuestionId().isEmpty()) throw new Exception(AnswerErrorMessages.MISSING_QUESTION_ID.getErrorMessage());
        if(newAnswer.getAnswer().isEmpty()) throw new Exception(AnswerErrorMessages.MISSING_ANSWER.getErrorMessage());

        AddAnswerDto answerDto = new AddAnswerDto();
        BeanUtils.copyProperties(newAnswer, answerDto);

        AddAnswerDto createdAnswer = this.answerService.createAnswer(answerDto);

        AddAnswerResponse returnValue = new AddAnswerResponse();
        returnValue.setAnswerId(createdAnswer.getAnswerId());
        returnValue.setUserId(createdAnswer.getUserId());
        returnValue.setQuestionId(createdAnswer.getQuestionId());
        returnValue.setAnswer(createdAnswer.getAnswer());
        returnValue.setDate(createdAnswer.getDate());

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping("showAllAnswers/ascending")
    ResponseEntity<List> displayAllAnswersInAscendingOrder () throws Exception {

        List info = answerService.answersInAscendingOrderOnTime();
        if (info == null) throw new Exception(QuestionErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("showAllAnswers/descending")
    ResponseEntity<List> displayAllAnswersInDescendingOrder () throws Exception {

        List info = answerService.answersInDescendingOrderOnTime();
        if (info == null) throw new Exception(QuestionErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @PostMapping("/vote")
    ResponseEntity<ServiceVotingDto> createNewAccount(@Valid @RequestBody ServiceVotingDto votingDto) throws Exception {

        if(votingDto.getVote().equals("UP_VOTE")){
            this.answerService.upVote(votingDto);
        } else if (votingDto.getVote().equals("DOWN_VOTE")) {
            this.answerService.downVote(votingDto);
        } else {
            throw new Exception(AnswerErrorMessages.ENTER_VALID_UPVOTE_OR_DOWN_VOTE.getErrorMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
