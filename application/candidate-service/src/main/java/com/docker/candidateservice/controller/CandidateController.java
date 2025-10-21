package com.docker.candidateservice.controller;

import com.docker.candidateservice.model.dto.CandidateDetailsDto;
import com.docker.candidateservice.model.dto.CandidateDto;
import com.docker.candidateservice.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("candidate")
public class CandidateController {

    @Autowired
    private CandidateService service;

    @GetMapping("/all")
    public Flux<CandidateDto> allCandidates(){
        return this.service.all();
    }

    @GetMapping("/{id}")
    public Mono<CandidateDetailsDto> getById(@PathVariable String id){
        return this.service.findById(id);
    }

    @PostMapping
    public Mono<CandidateDto> save(@RequestBody Mono<CandidateDto> mono){
        return this.service.save(mono);
    }


}
