package com.docker.jobservice.repository;

import com.docker.jobservice.model.entity.Job;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Set;

// https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongodb.repositories.queries
@Repository
public interface JobRepo extends ReactiveMongoRepository<Job, String> {

    Flux<Job> findBySkillsIn(Set<String> skills);
}
