package com.twl.pollservice.repository;

import com.twl.pollservice.model.entity.Vote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends MongoRepository<Vote, String> {
}
