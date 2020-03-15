package com.twl.pollservice.service.validator;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.model.entity.Vote;
import com.twl.pollservice.repository.PollSessionRepository;
import com.twl.pollservice.repository.VoteRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class VoteValidator {

    private final VoteRepository voteRepository;
    private final PollSessionRepository pollSessionRepository;

    public VoteValidator(VoteRepository voteRepository, PollSessionRepository pollSessionRepository) {
        this.voteRepository = voteRepository;
        this.pollSessionRepository = pollSessionRepository;
    }

    public void validate(Vote vote) throws Exception {

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
