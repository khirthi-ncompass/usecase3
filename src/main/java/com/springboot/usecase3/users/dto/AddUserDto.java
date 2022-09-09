package com.springboot.usecase3.users.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserDto {
    private String userId;
    private String emailId;
    private String password;
}
