package com.twl.pollservice.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollSession {

    @Id
    private String id;

    @NotNull(message = "Field 'sessionStart' is required!")
    private LocalDateTime sessionStart;

    private LocalDateTime sessionEnd;

    @NotNull(message = "Field 'poll' is required!")
    private Poll poll;
}
