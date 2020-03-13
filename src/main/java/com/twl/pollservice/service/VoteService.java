package com.twl.pollservice.service;

import com.twl.pollservice.model.entity.Vote;
import com.twl.pollservice.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public Vote save(Vote vote) {
        // TODO validações (verificar sessão aberta e se já não votou na pauta)
        return repository.save(vote);
    }

    // TODO placeholder method - test purpose
    public List<Vote> findAll() {
        return repository.findAll();
    }
}
