package com.docker.candidateservice.repository;

import com.docker.candidateservice.model.entity.Candidate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepo extends ReactiveMongoRepository<Candidate, String> {
}
