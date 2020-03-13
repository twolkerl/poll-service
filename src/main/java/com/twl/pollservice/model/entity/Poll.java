package com.twl.pollservice.model.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
public class Poll {

    @Id
    private String id;
    @NotBlank(message = "Poll title is required!")
    private String title;
    @NotBlank(message = "Poll description is required!")
    private String description;
    private PollSession session;
}
