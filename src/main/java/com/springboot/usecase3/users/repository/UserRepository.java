package com.springboot.usecase3.users.repository;



import com.springboot.usecase3.users.dto.AnswersByUserIdDto;
import com.springboot.usecase3.users.dto.QuestionsByUserIdDto;
import com.springboot.usecase3.users.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    User createUser(User newQuestion);

    List<QuestionsByUserIdDto> questionsAskedByUserId(String userId);

    List<AnswersByUserIdDto> answersAnsweredByUserId(String userId);
}
