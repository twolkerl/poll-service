package com.twl.pollservice.service;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.exception.NotFoundException;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.repository.PollSessionRepository;
import com.twl.pollservice.service.validator.PollSessionValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class PollSessionService {

    private static final int SIXTY_SECONDS = 60;

    private final PollSessionRepository repository;
    private final PollSessionValidator validator;

    public PollSessionService(PollSessionRepository repository, PollSessionValidator validator) {
        this.repository = repository;
        this.validator = validator;
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
        validator.validateSessionExists(pollSession);

        if (Objects.isNull(pollSession.getSessionEnd())) {

            LocalDateTime end = pollSession.getSessionStart().plusSeconds(SIXTY_SECONDS);
            pollSession.setSessionEnd(end);
        } else {

            validator.validateDateRange(pollSession);
        }
    }
}
