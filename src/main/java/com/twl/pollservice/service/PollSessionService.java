package com.twl.pollservice.service;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.exception.NotFoundException;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.repository.PollSessionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PollSessionService {

    private static final int SIXTY_SECONDS = 60;

    private final PollSessionRepository repository;

    public PollSessionService(PollSessionRepository repository) {
        this.repository = repository;
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

    private void validateAndPrepare(PollSession pollSession) throws BusinessException {
        validateSessionExists(pollSession);

        if (Objects.isNull(pollSession.getSessionEnd())) {

            LocalDateTime end = pollSession.getSessionStart().plusSeconds(SIXTY_SECONDS);
            pollSession.setSessionEnd(end);
        } else {

            validateDateRange(pollSession);
        }
    }

    private void validateDateRange(PollSession pollSession) throws BusinessException {
        if (pollSession.getSessionStart().isAfter(pollSession.getSessionEnd())) {
            throw new BusinessException("Session end must be after session start!");
        }
    }

    private void validateSessionExists(PollSession pollSession) throws BusinessException {

        String pollId = pollSession.getPoll().getId();

        if (repository.findByPollId(pollId).isPresent()) {
            throw new BusinessException("A session have already been created for given poll.");
        }
    }
}
