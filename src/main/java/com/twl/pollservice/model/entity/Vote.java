package com.twl.pollservice.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Vote {

    @Id
    private String id;

    @NotBlank(message = "Field 'cpf' is required!")
    private String cpf;

    @NotNull(message = "Field 'voteOption' is required!")
    private Boolean voteOption;

    @NotNull(message = "Field 'poll' is required!")
    private Poll poll;
}
