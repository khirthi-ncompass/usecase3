package com.springboot.usecase3.users.repository;


import com.springboot.usecase3.users.dto.AnswersByUserIdDto;
import com.springboot.usecase3.users.dto.QuestionsByUserIdDto;
import com.springboot.usecase3.users.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@SuppressWarnings("All")
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class QuestionsByUserIdMapper implements RowMapper<QuestionsByUserIdDto> {

        @Override
        public QuestionsByUserIdDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            QuestionsByUserIdDto questionsByUserIdDto = new QuestionsByUserIdDto();
            questionsByUserIdDto.setUserId(rs.getString("userId"));
            questionsByUserIdDto.setQuestionId(rs.getString("questionId"));
            questionsByUserIdDto.setDate(rs.getString("questionDate"));
            questionsByUserIdDto.setQuestion(rs.getString("question"));
            return questionsByUserIdDto;
        }
    }

    class AnswersByUserIdMapper implements RowMapper<AnswersByUserIdDto> {

        @Override
        public AnswersByUserIdDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            AnswersByUserIdDto answersByUserIdDto = new AnswersByUserIdDto();
            answersByUserIdDto.setUserId(rs.getString("userId"));
            answersByUserIdDto.setAnswerId(rs.getString("answerId"));
            answersByUserIdDto.setDate(rs.getString("answerDate"));
            answersByUserIdDto.setAnswer(rs.getString("answer"));
            return answersByUserIdDto;
        }
    }

    @Override
    public User createUser(User newQuestion) {

        String query = UserQueries.CREATE_ACCOUNT;

        this.jdbcTemplate.update(query,
                newQuestion.getUserId(),
                newQuestion.getEmailId(),
                newQuestion.getPassword());
        return newQuestion;
    }

    @Override
    public List<QuestionsByUserIdDto> questionsAskedByUserId(String userId) {

        String query = UserQueries.QUESTIONS_ASKED_BY_USER_ID;

        return jdbcTemplate.query(query, new Object[]{userId}, new QuestionsByUserIdMapper());
    }

    @Override
    public List<AnswersByUserIdDto> answersAnsweredByUserId(String userId) {

        String query = UserQueries.ANSWERS_ANSWERED_BY_USER_ID;

        return jdbcTemplate.query(query, new Object[]{userId}, new AnswersByUserIdMapper());
    }
}
