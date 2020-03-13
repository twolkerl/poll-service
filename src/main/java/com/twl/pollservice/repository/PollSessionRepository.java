package com.twl.pollservice.repository;

import com.twl.pollservice.model.entity.PollSession;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollSessionRepository extends MongoRepository<PollSession, String> {
}
