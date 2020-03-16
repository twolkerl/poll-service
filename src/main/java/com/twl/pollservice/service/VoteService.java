package com.twl.pollservice.service;

import com.twl.pollservice.model.entity.Vote;
import com.twl.pollservice.repository.VoteRepository;
import com.twl.pollservice.service.validator.VoteValidator;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository repository;
    private final VoteValidator validator;

    public VoteService(VoteRepository repository, VoteValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Vote save(Vote vote) throws Exception {
        validator.validate(vote);
        return repository.save(vote);
    }
}
