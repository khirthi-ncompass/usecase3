package com.springboot.usecase3.answers.repository;


import com.springboot.usecase3.answers.dto.DisplayAllAnswersDto;
import com.springboot.usecase3.answers.dto.VotingDto;
import com.springboot.usecase3.answers.entity.Answer;
import com.springboot.usecase3.questions.dto.DisplayAllQuestionsDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository {
    Answer createAnswer(Answer newAnswer);

    List<DisplayAllAnswersDto> answersInAscendingOrderOnTime();

    List<DisplayAllAnswersDto> answersInDescendingOrderOnTime();

    void upVote(VotingDto votingDto) throws Exception;
    void downVote(VotingDto votingDto) throws Exception;

    Answer getAnswerById(String answerId) throws Exception;
}
