package com.docker.jobservice.controller;

import com.docker.jobservice.model.dto.JobDto;
import com.docker.jobservice.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("job")
@Slf4j
public class JobController {

    @Autowired
    private JobService service;

    @GetMapping("/all")
    public Flux<JobDto> allJobs() {
        return service.allJobs();
    }

    @GetMapping("/search")
    public Flux<JobDto> search(@RequestParam Set<String> skills) {
        log.info("Skills: {}", skills);
        return service.findBySkillsIn(skills);
    }

    @PostMapping
    public Mono<JobDto> save(@RequestBody Mono<JobDto> job) {
        return service.saveJob(job);
    }

}
