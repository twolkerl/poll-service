package com.twl.pollservice.service;

import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.repository.PollSessionRepository;
import org.springframework.stereotype.Service;

@Service
public class PollSessionService {

    private final PollSessionRepository repository;

    public PollSessionService(PollSessionRepository repository) {
        this.repository = repository;
    }

    public PollSession save(PollSession pollSession) {
        return repository.save(pollSession);
    }
}
