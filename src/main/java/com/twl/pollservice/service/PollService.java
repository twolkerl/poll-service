package com.twl.pollservice.service;

import com.twl.pollservice.exception.BusinessException;
import com.twl.pollservice.exception.NotFoundException;
import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.model.entity.PollSession;
import com.twl.pollservice.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PollService {

    private final PollRepository repository;

    public PollService(PollRepository repository) {
        this.repository = repository;
    }

    public Poll save(Poll poll) {
        return repository.save(poll);
    }

    public List<Poll> findAll() {
        return repository.findAll();
    }

    public Optional<Poll> findOne(String id) {
        return repository.findById(id);
    }

    public Poll openSession(String id, PollSession session) throws Exception {

        Poll poll = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Poll not found."));

        if (Objects.isNull(poll.getSession())) {

            // TODO validar data final deve ser após data inicial

            if (Objects.isNull(session.getSessionEnd())) {
                LocalDateTime end = session.getSessionStart().plusSeconds(60);
                session.setSessionEnd(end);
            }

            poll.setSession(session);
            return save(poll);
        } else {
            throw new BusinessException("A session have already been created for given poll.");
        }
    }
}
