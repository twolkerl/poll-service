package com.twl.pollservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poll {

    @Id
    private String id;

    @NotBlank(message = "Field 'title' is required!")
    private String title;

    @NotBlank(message = "Field 'description' is required!")
    private String description;
}
