package com.springboot.usecase3.users.service;

import com.springboot.usecase3.users.dto.AddUserDto;
import com.springboot.usecase3.users.dto.AnswersByUserIdDto;
import com.springboot.usecase3.users.dto.QuestionsByUserIdDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    AddUserDto createUser (AddUserDto newUser) throws Exception;

    List<QuestionsByUserIdDto> questionsAskedByUserId(String userId);

    List<AnswersByUserIdDto> answersAnsweredByUserId(String userId);
}
