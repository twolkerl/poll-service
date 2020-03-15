package com.twl.pollservice.service;

import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
