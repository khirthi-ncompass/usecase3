package com.springboot.usecase3.answers.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ServiceVotingDto {

    @NotNull(message = "answerId cannot be null")
    private String answerId;

    @NotNull(message = "vote cannot be null")
    private String voteStatus;
}
