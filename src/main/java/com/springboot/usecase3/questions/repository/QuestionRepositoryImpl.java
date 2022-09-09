package com.springboot.usecase3.questions.repository;

import com.springboot.usecase3.questions.dto.AnswersToQuestionIdDto;
import com.springboot.usecase3.questions.dto.DisplayAllQuestionsDto;
import com.springboot.usecase3.questions.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings("all")
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class AnswersToQuestionIdMapper implements RowMapper<AnswersToQuestionIdDto> {

        @Override
        public AnswersToQuestionIdDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            AnswersToQuestionIdDto answersToQuestionIdDto = new AnswersToQuestionIdDto();

            answersToQuestionIdDto.setQuestionId(rs.getString("questionId"));
            answersToQuestionIdDto.setQuestionDate(rs.getString("questionDate"));
            answersToQuestionIdDto.setQuestion(rs.getString("question"));

            answersToQuestionIdDto.setAnswerId(rs.getString("answerId"));
            answersToQuestionIdDto.setAnswerDate(rs.getString("answerDate"));
            answersToQuestionIdDto.setAnswer(rs.getString("answer"));
            return answersToQuestionIdDto;
        }
    }

    class DisplayAllQuestionsMapper implements RowMapper<DisplayAllQuestionsDto> {

        @Override
        public DisplayAllQuestionsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            DisplayAllQuestionsDto displayAllQuestions = new DisplayAllQuestionsDto();

            displayAllQuestions.setQuestionId(rs.getString("questionId"));
            displayAllQuestions.setUserId(rs.getString("userId"));
            displayAllQuestions.setQuestion(rs.getString("question"));
            displayAllQuestions.setDate(rs.getString("date"));
            return displayAllQuestions;
        }
    }


    @Override
    public Question createQuestion(Question newQuestion) {

        String query = QuestionQueries.ADD_QUESTION;
        this.jdbcTemplate.update(query,
                newQuestion.getQuestionId(),
                newQuestion.getUserId(),
                newQuestion.getQuestion(),
                newQuestion.getDate());
        return newQuestion;
    }

    @Override
    public List<AnswersToQuestionIdDto> answersToQuestionId(String questionId) {

        String query = QuestionQueries.ANSWERS_TO_QUESTION_ID;

        return jdbcTemplate.query(query, new Object[]{questionId}, new AnswersToQuestionIdMapper());
    }

    @Override
    public List<DisplayAllQuestionsDto> questionsInAscendingOrderOnTime() {

        String query = QuestionQueries.DISPLAY_QUESTIONS_ASCENDING_ORDER;

        return jdbcTemplate.query(query, new DisplayAllQuestionsMapper());
    }

    @Override
    public List<DisplayAllQuestionsDto> questionsInDescendingOrderOnTime() {

        String query = QuestionQueries.DISPLAY_QUESTIONS_DESCENDING_ORDER;

        return jdbcTemplate.query(query, new DisplayAllQuestionsMapper());
    }
}
