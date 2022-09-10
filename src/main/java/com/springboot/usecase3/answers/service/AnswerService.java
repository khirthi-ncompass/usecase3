package com.springboot.usecase3.answers.service;

import com.springboot.usecase3.answers.dto.AddAnswerDto;
import com.springboot.usecase3.answers.dto.DisplayAllAnswersDto;
import com.springboot.usecase3.answers.dto.ServiceVotingDto;
import com.springboot.usecase3.answers.dto.VotingDto;
import com.springboot.usecase3.answers.entity.Answer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnswerService {
    AddAnswerDto createAnswer (AddAnswerDto newAnswer) throws Exception;

    List<DisplayAllAnswersDto> answersInAscendingOrderOnTime();

    List<DisplayAllAnswersDto> answersInDescendingOrderOnTime();

    void vote(ServiceVotingDto votingDto) throws Exception;

    Answer getAnswerById(String answerId) throws Exception;

}
