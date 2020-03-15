package com.twl.pollservice.service;

import com.twl.pollservice.exception.NotFoundException;
import com.twl.pollservice.model.entity.Poll;
import com.twl.pollservice.repository.PollRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Poll findOne(String id) throws Exception {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Poll not found."));
    }
}
