package com.docker.jobservice.service;

import com.docker.jobservice.model.dto.JobDto;
import com.docker.jobservice.repository.JobRepo;
import com.docker.jobservice.util.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class JobService {

    @Autowired
    private JobRepo repo;

    public Flux<JobDto> allJobs() {
        return this.repo.findAll().map(MapUtil::toDto);
    }

    public Flux<JobDto> findBySkillsIn(Set<String> skills) {
        return this.repo.findBySkillsIn(skills).map(MapUtil::toDto);
    }

    public Mono<JobDto> saveJob(Mono<JobDto> job) {
        return job.map(MapUtil::toEntity)
                .flatMap(this.repo::save)
                .map(MapUtil::toDto);
    }
}
