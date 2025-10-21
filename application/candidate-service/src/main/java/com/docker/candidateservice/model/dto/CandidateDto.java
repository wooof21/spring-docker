package com.docker.candidateservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@SuperBuilder
public class CandidateDto {

    private String id;
    private String name;
    private Set<String> skills;
}
