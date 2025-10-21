package com.docker.candidateservice.service;

import com.docker.candidateservice.client.JobClient;
import com.docker.candidateservice.model.dto.CandidateDetailsDto;
import com.docker.candidateservice.model.dto.CandidateDto;
import com.docker.candidateservice.repository.CandidateRepo;
import com.docker.candidateservice.util.MapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CandidateService {

    @Autowired
    private CandidateRepo repo;

    @Autowired
    private JobClient client;

    public Flux<CandidateDto> all() {
        return this.repo.findAll().map(MapUtil::toDto);
    }

    public Mono<CandidateDetailsDto> findById(String id) {
        return this.repo.findById(id)
                .map(MapUtil::toDetailsDto)
                .flatMap(this::appendRecommendedJobs);
    }

    private Mono<CandidateDetailsDto> appendRecommendedJobs(CandidateDetailsDto detailsDto) {
        log.info("CandidateDetailsDto: {}", detailsDto);
        return this.client.getRecommendedJobs(detailsDto.getSkills())
                .doOnNext(l -> {
                    log.info("Recommended Jobs: {}", l);
                    detailsDto.setRecommendedJobs(l);
                })
                .thenReturn(detailsDto);
    }

    public Mono<CandidateDto> save(Mono<CandidateDto> dto) {
        return dto.map(MapUtil::toEntity)
                .flatMap(this.repo::save)
                .map(MapUtil::toDto);
    }
}
