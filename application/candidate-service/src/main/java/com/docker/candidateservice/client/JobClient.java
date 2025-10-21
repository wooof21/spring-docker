package com.docker.candidateservice.client;

import com.docker.candidateservice.model.dto.JobDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class JobClient {

    private final WebClient client;

    // xxx/job/search?skills=
    public JobClient(@Value("${job.service.url}") String baseUrl) {
        log.info("Base URL: {}", baseUrl);
        this.client = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<List<JobDto>> getRecommendedJobs(Set<String> skills) {
        log.info("RecommendedJobs Skills : {}", skills);
        return this.client.get()
                .uri(u -> {
                    URI uri = u.path("/search").queryParam("skills", skills).build();
                    log.info("URI: {}", uri);
                    return uri;
                })
                .retrieve()
                .bodyToFlux(JobDto.class)
                .collectList()
                .onErrorReturn(Collections.emptyList());
    }
}
