package com.docker.candidateservice.model.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class CandidateDetailsDto extends CandidateDto {

    private List<JobDto> recommendedJobs;
}
