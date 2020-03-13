package com.twl.pollservice.model.entity;

import com.twl.pollservice.model.enums.SessionStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class PollSession {

    @NotNull(message = "Session start time is required!")
    private LocalDateTime sessionStart;
    private LocalDateTime sessionEnd;
    @NotNull(message = "Session status is required!")
    private SessionStatus status;
    private boolean result;
}