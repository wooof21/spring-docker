package com.docker.candidateservice.util;

import com.docker.candidateservice.model.dto.CandidateDetailsDto;
import com.docker.candidateservice.model.dto.CandidateDto;
import com.docker.candidateservice.model.entity.Candidate;

public class MapUtil {

    public static CandidateDto toDto(Candidate candidate) {
        return CandidateDto.create(
                candidate.getId(),
                candidate.getName(),
                candidate.getSkills()
        );
    }

    public static CandidateDetailsDto toDetailsDto(Candidate candidate) {
        return CandidateDetailsDto.builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .skills(candidate.getSkills())
                .build();
    }

    public static Candidate toEntity(CandidateDto dto) {
        return Candidate.create(
                dto.getId(),
                dto.getName(),
                dto.getSkills()
        );
    }
}
