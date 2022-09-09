package com.springboot.usecase3.users.entity.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddUserRequest {


    @NotNull(message = "emailId cannot be null")
    private String emailId;

    @NotNull(message = "password cannot be null")
    private String password;
}
