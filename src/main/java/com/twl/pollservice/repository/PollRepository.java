package com.twl.pollservice.repository;

import com.twl.pollservice.model.entity.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends MongoRepository<Poll, String> {
}
