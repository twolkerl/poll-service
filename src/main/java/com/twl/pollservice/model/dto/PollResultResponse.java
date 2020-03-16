package com.twl.pollservice.model.dto;

import com.twl.pollservice.model.enums.PollResult;
import com.twl.pollservice.model.enums.SessionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollResultResponse {

    private PollResult result;
    private Long totalVoteCount;
    private Long totalYesVotes;
    private Long totalNoVotes;
    private SessionStatus status;
}
