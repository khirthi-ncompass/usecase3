package com.springboot.usecase3.users.service;


import com.springboot.usecase3.questions.exceptions.QuestionErrorMessages;
import com.springboot.usecase3.users.dto.AddUserDto;
import com.springboot.usecase3.users.dto.AnswersByUserIdDto;
import com.springboot.usecase3.users.dto.QuestionsByUserIdDto;
import com.springboot.usecase3.users.entity.User;
import com.springboot.usecase3.users.exceptions.UserErrorMessages;
import com.springboot.usecase3.users.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddUserDto createUser(AddUserDto newUser) throws Exception {

        User userEntity = new User();
        BeanUtils.copyProperties(newUser, userEntity);

        String userId = com.springboot.usecase3.users.service.UserIdGenerator.generateUserId(6);
        userEntity.setUserId("USER" + userId);

        this.userRepository.createUser(userEntity);

        AddUserDto returnedValue = new AddUserDto();

        BeanUtils.copyProperties(userEntity, returnedValue);

        return returnedValue;
    }

    @Override
    public List<QuestionsByUserIdDto> questionsAskedByUserId(String userId) {
        try {
            List returnValue = userRepository.questionsAskedByUserId(userId);
            if(returnValue.isEmpty()) {
                throw new Exception(UserErrorMessages.USER_ID_DOESNT_EXIST.getErrorMessage());
            } else {
                return returnValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<AnswersByUserIdDto> answersAnsweredByUserId(String userId) {
        try {
            List returnValue = userRepository.answersAnsweredByUserId(userId);
            if(returnValue.isEmpty()) {
                throw new Exception(UserErrorMessages.USER_ID_DOESNT_EXIST.getErrorMessage());
            } else {
                return returnValue;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
