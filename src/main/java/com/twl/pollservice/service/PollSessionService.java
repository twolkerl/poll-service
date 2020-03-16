package com.twl.pollservice.service;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.exception.NotFoundException;
import com.twl.pollservice.model.dto.PollResultResponse;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.model.entity.Vote;
import com.twl.pollservice.model.enums.PollResult;
import com.twl.pollservice.model.enums.SessionStatus;
import com.twl.pollservice.repository.PollSessionRepository;
import com.twl.pollservice.repository.VoteRepository;
import com.twl.pollservice.service.validator.PollSessionValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.twl.pollservice.model.enums.SessionStatus.CLOSED;
import static com.twl.pollservice.model.enums.SessionStatus.OPEN;

@Service
public class PollSessionService {

    private static final int SIXTY_SECONDS = 60;

    private final PollSessionRepository repository;
    private final PollSessionValidator validator;
    private final VoteRepository voteRepository;

    public PollSessionService(PollSessionRepository repository, PollSessionValidator validator,
                              VoteRepository voteRepository) {

        this.repository = repository;
        this.validator = validator;
        this.voteRepository = voteRepository;
    }

    public PollSession save(PollSession pollSession) throws BusinessException {

        validateAndPrepare(pollSession);
        return repository.save(pollSession);
    }

    public PollSession findByPollId(String pollId) throws NotFoundException {
        return repository.findByPollId(pollId).orElseThrow(NotFoundException::new);
    }

    public void delete(String id) throws NotFoundException {
        PollSession pollSession = repository.findById(id).orElseThrow(NotFoundException::new);
        repository.delete(pollSession);
    }

    public PollResultResponse getSessionResult(String id) throws Exception {

        PollSession session = repository.findById(id).orElseThrow(NotFoundException::new);

        List<Vote> votes = voteRepository.findAllByPollId(session.getPoll().getId());

        SessionStatus status = session.getSessionEnd().isBefore(LocalDateTime.now()) ? CLOSED : OPEN;

        long totalVotes = votes.size();
        long trueCount = countByVoteOption(votes, Boolean.TRUE);
        long falseCount = countByVoteOption(votes, Boolean.FALSE);
        PollResult result = setPollResult(trueCount, falseCount);

        // TODO Kafka/Rabbit para enviar mensagem quando a sessão estiver encerrada

        return PollResultResponse.builder()
                .result(result)
                .status(status)
                .totalVoteCount(totalVotes)
                .totalYesVotes(trueCount)
                .totalNoVotes(falseCount)
                .build();
    }

    private long countByVoteOption(List<Vote> votes, Boolean voteOption) {
        return votes.stream().filter(vote -> vote.getVoteOption() == voteOption).count();
    }

    private PollResult setPollResult(long trueCount, long falseCount) {

        PollResult result;

        if (trueCount == falseCount) {
            result = PollResult.DRAW;
        } else if (trueCount > falseCount) {
            result = PollResult.YES;
        } else {
            result = PollResult.NO;
        }
        return result;
    }

    private void validateAndPrepare(PollSession pollSession) throws BusinessException {
        validator.validateSessionExists(pollSession);

        if (Objects.isNull(pollSession.getSessionEnd())) {

            LocalDateTime end = pollSession.getSessionStart().plusSeconds(SIXTY_SECONDS);
            pollSession.setSessionEnd(end);
        } else {

            validator.validateDateRange(pollSession);
        }
    }
}
