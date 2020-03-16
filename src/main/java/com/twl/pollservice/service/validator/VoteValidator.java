package com.twl.pollservice.service.validator;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.integration.UserInfoIntegration;
import com.twl.pollservice.integration.response.UserInfoResponse;
import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.model.entity.Vote;
import com.twl.pollservice.model.enums.CpfStatus;
import com.twl.pollservice.repository.PollSessionRepository;
import com.twl.pollservice.repository.VoteRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class VoteValidator {

    private final VoteRepository voteRepository;
    private final PollSessionRepository pollSessionRepository;
    private final UserInfoIntegration userInfoIntegration;

    public VoteValidator(VoteRepository voteRepository, PollSessionRepository pollSessionRepository,
                         UserInfoIntegration userInfoIntegration) {

        this.voteRepository = voteRepository;
        this.pollSessionRepository = pollSessionRepository;
        this.userInfoIntegration = userInfoIntegration;
    }

    public void validate(Vote vote) throws Exception {

        UserInfoResponse userInfoResponse = userInfoIntegration.checkCpfStatus(vote.getCpf());

        if (CpfStatus.UNABLE_TO_VOTE == userInfoResponse.getStatus()) {
            throw new BusinessException("CPF is unable to vote.");
        } else {

            Poll poll = vote.getPoll();

            Optional<PollSession> pollSession = pollSessionRepository.findByPollId(poll.getId());

            if (!pollSession.isPresent()
                    || LocalDateTime.now().isAfter(pollSession.get().getSessionEnd())
                    || LocalDateTime.now().isBefore(pollSession.get().getSessionStart())) {

                throw new BusinessException("Voting session closed for given poll.");
            }

            if (voteRepository.findByCpfAndPollId(vote.getCpf(), poll.getId()).isPresent()) {
                throw new BusinessException("Person already voted in this poll.");
            }
        }
    }
}
