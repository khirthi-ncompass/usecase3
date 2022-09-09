package com.springboot.usecase3.answers.repository;

import com.springboot.usecase3.answers.dto.DisplayAllAnswersDto;
import com.springboot.usecase3.answers.dto.VotingDto;
import com.springboot.usecase3.answers.entity.Answer;
import com.springboot.usecase3.questions.dto.DisplayAllQuestionsDto;
import com.springboot.usecase3.questions.repository.QuestionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    class DisplayAllAnswersMapper implements RowMapper<DisplayAllAnswersDto> {

        @Override
        public DisplayAllAnswersDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            DisplayAllAnswersDto displayAllAnswers = new DisplayAllAnswersDto();

            displayAllAnswers.setAnswerId(rs.getString("answerId"));
            displayAllAnswers.setAnswer(rs.getString("answer"));
            displayAllAnswers.setDate(rs.getString("date"));
            return displayAllAnswers;
        }
    }

    private final BeanPropertyRowMapper<Answer> answerBeanRowMapper =
            new BeanPropertyRowMapper<>(Answer.class);

    private final static String ADD_ANSWER = "INSERT INTO ANSWERS (answerId, userId, questionId, answer, date) VALUES (?,?,?,?,?)";

    /*DISPLAY ALL QUESTIONS WITH SORTING ACCORDING TO TIME (DESC)*/
    public static final String DISPLAY_ANSWERS_DESCENDING_ORDER =  "SELECT answerId, answer, date FROM ANSWERS order by date desc;";

    /*DISPLAY ALL QUESTIONS WITH SORTING ACCORDING TO TIME (ASC)*/
    public static final String DISPLAY_ANSWERS_ASCENDING_ORDER =  "SELECT answerId, answer, date FROM ANSWERS order by date asc;";

    public static final String UP_VOTE = "UPDATE ANSWER SET upVote = ? where answerId = ?";

    public static final String DOWN_VOTE = "UPDATE ANSWER SET downVote = ? where answerId = ?";

    private static final String GET_ANSWER_USING_ANSWER_ID = "SELECT * FROM ANSWER WHERE answerId =?";

    @Override
    public Answer createAnswer(Answer newAnswer) {
        this.jdbcTemplate.update(ADD_ANSWER,
                newAnswer.getAnswerId(),
                newAnswer.getUserId(),
                newAnswer.getQuestionId(),
                newAnswer.getAnswer(),
                newAnswer.getDate());
        return newAnswer;
    }

    @Override
    public List<DisplayAllAnswersDto> answersInAscendingOrderOnTime() {
        return jdbcTemplate.query(DISPLAY_ANSWERS_ASCENDING_ORDER, new DisplayAllAnswersMapper());
    }

    @Override
    public List<DisplayAllAnswersDto> answersInDescendingOrderOnTime() {
        return jdbcTemplate.query(DISPLAY_ANSWERS_DESCENDING_ORDER, new DisplayAllAnswersMapper());
    }

    @Override
    public void upVote(VotingDto votingDto) throws Exception {
        String answerId = votingDto.getAnswerId();

        Answer answer = this.getAnswerById(votingDto.getAnswerId());
        int currentUpVoteStatus = answer.getUpVote();

        currentUpVoteStatus += 1;
        answer.setUpVote(currentUpVoteStatus);

        this.jdbcTemplate.update(UP_VOTE, currentUpVoteStatus, answerId);
    }

    @Override
    public void downVote(VotingDto votingDto) throws Exception {
        int vote = votingDto.getVote();
        String answerId = votingDto.getAnswerId();

        Answer answer = this.getAnswerById(votingDto.getAnswerId());
        int currentDownVoteStatus = answer.getUpVote();

        currentDownVoteStatus += 1;
        answer.setUpVote(currentDownVoteStatus);

        this.jdbcTemplate.update(DOWN_VOTE, currentDownVoteStatus, answerId);
    }

    @Override
    public Answer getAnswerById(String answerId) throws Exception {
        Answer answer;
        try{
            answer = this.jdbcTemplate.queryForObject(GET_ANSWER_USING_ANSWER_ID, new Object[]{answerId}, this.answerBeanRowMapper);
        }catch (EmptyResultDataAccessException e){
            answer = null;
        }
        if (answer == null) {
            throw new Exception("Account with accountNumber "+answerId+" does not exist");
        }
        return answer;
    }
}
