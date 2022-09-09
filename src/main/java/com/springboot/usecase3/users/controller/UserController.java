package com.springboot.usecase3.users.controller;

import com.springboot.usecase3.users.dto.AddUserDto;
import com.springboot.usecase3.users.dto.AnswersByUserIdDto;
import com.springboot.usecase3.users.dto.QuestionsByUserIdDto;
import com.springboot.usecase3.users.entity.request.AddUserRequest;
import com.springboot.usecase3.users.entity.response.AddUserResponse;
import com.springboot.usecase3.users.exceptions.UserErrorMessages;
import com.springboot.usecase3.users.service.UserService;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("create-user")
    ResponseEntity<AddUserResponse> createUser(@Valid @RequestBody AddUserRequest newUser) throws Exception {

        if(newUser.getEmailId().isEmpty()) throw new Exception(UserErrorMessages.MISSING_EMAIL_ID.getErrorMessage());
        if(newUser.getPassword().isEmpty()) throw new Exception(UserErrorMessages.MISSING_PASSWORD.getErrorMessage());

        AddUserDto userDto = new AddUserDto();
        BeanUtils.copyProperties(newUser, userDto);

        AddUserDto createdUser = this.userService.createUser(userDto);

        AddUserResponse createdUserResponse = new AddUserResponse();

        createdUserResponse.setUserID(createdUser.getUserId());
        createdUserResponse.setEmailID(createdUser.getEmailId());

        return new ResponseEntity<>(createdUserResponse, HttpStatus.OK);
    }

    @GetMapping("questions/{userId}")
    ResponseEntity<List>  getQuestionsByUserId (@PathVariable String userId) throws Exception {

        List info = userService.questionsAskedByUserId(userId);
        if (info == null) throw new Exception(UserErrorMessages.USER_ID_DOESNT_EXIST.getErrorMessage());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }

    @GetMapping("answers/{userId}")
    ResponseEntity<List> answersByUserId (@PathVariable String userId) throws Exception {

        List info = userService.answersAnsweredByUserId(userId);
        if (info == null) throw new Exception(UserErrorMessages.USER_ID_DOESNT_EXIST.getErrorMessage());

        return new ResponseEntity<>(info, HttpStatus.OK);
    }
}
