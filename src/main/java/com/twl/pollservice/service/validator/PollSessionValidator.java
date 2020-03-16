package com.twl.pollservice.service.validator;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.repository.PollSessionRepository;
import org.springframework.stereotype.Component;

@Component
public class PollSessionValidator {

    private final PollSessionRepository repository;

    public PollSessionValidator(PollSessionRepository repository) {
        this.repository = repository;
    }

    public boolean validateDateRange(PollSession pollSession) throws BusinessException {
        if (pollSession.getSessionStart().isAfter(pollSession.getSessionEnd())) {
            throw new BusinessException("Session end must be after session start!");
        }
        return true;
    }

    public boolean validateSessionExists(PollSession pollSession) throws BusinessException {

        String pollId = pollSession.getPoll().getId();

        if (repository.findByPollId(pollId).isPresent()) {
            throw new BusinessException("A session have already been created for given poll.");
        }
        return true;
    }
}
