package com.twl.pollservice.model.entity;

import com.twl.pollservice.model.enums.SessionStatus;
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

    @NotNull(message = "Field 'status' is required!")
    private SessionStatus status = SessionStatus.OPEN;

    private Boolean result;

    @NotNull(message = "Field 'poll' is required!")
    private Poll poll;
}
