package com.twl.pollservice.repository;

import com.twl.pollservice.model.entity.PollSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PollSessionRepository extends MongoRepository<PollSession, String> {

    Optional<PollSession> findByPollId(String pollId);
}
