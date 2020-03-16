package com.twl.pollservice.repository;

import com.twl.pollservice.model.entity.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {

    Optional<Vote> findByCpfAndPollId(String cpf, String pollId);
    List<Vote> findAllByPollId(String pollId);
}
