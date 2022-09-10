package com.springboot.usecase3.answers.service;

import com.springboot.usecase3.answers.dto.AddAnswerDto;
import com.springboot.usecase3.answers.dto.DisplayAllAnswersDto;
import com.springboot.usecase3.answers.dto.ServiceVotingDto;
import com.springboot.usecase3.answers.dto.VotingDto;
import com.springboot.usecase3.answers.entity.Answer;
import com.springboot.usecase3.answers.exceptions.AnswerErrorMessages;
import com.springboot.usecase3.answers.repository.AnswerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public AddAnswerDto createAnswer(AddAnswerDto newAnswer) throws Exception {

        Answer answerEntity = new Answer();
        BeanUtils.copyProperties(newAnswer, answerEntity);

        String answerId = AnswerUtils.generateAnswerId(6);
        answerEntity.setAnswerId("ANS" + answerId);

        String date = AnswerUtils.generateDate();
        answerEntity.setDate(date);

        this.answerRepository.createAnswer(answerEntity);

        AddAnswerDto returnedValue = new AddAnswerDto();
        BeanUtils.copyProperties(answerEntity, returnedValue);

        return returnedValue;
    }

    @Override
    public List<DisplayAllAnswersDto> answersInAscendingOrderOnTime() {
        return this.answerRepository.answersInAscendingOrderOnTime();
    }

    @Override
    public List<DisplayAllAnswersDto> answersInDescendingOrderOnTime() {
        return this.answerRepository.answersInDescendingOrderOnTime();
    }

    @Override
    public void vote(ServiceVotingDto votingDto) throws Exception {
        Answer answer = this.answerRepository.getAnswerById(votingDto.getAnswerId());

        if (votingDto.getVoteStatus().equals("UP_VOTE")) {

            answer.setUpVote(answer.getUpVote() + 1);

            VotingDto result = new VotingDto();
            result.setAnswerId(answer.getAnswerId());
            result.setVote(answer.getUpVote());

            this.answerRepository.upVote(result);

        } else if (votingDto.getVoteStatus().equals("DOWN_VOTE")) {

            answer.setDownVote(answer.getDownVote() + 1);

            VotingDto result = new VotingDto();
            result.setAnswerId(answer.getAnswerId());
            result.setVote(answer.getDownVote());

            this.answerRepository.downVote(result);

        } else {
            throw new Exception(AnswerErrorMessages.ENTER_VALID_UPVOTE_OR_DOWN_VOTE.getErrorMessage());
        }
    }

    @Override
    public Answer getAnswerById(String answerId) throws Exception {
        return this.answerRepository.getAnswerById(answerId);
    }
}
