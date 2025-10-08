package com.docker.jobservice.util;

import com.docker.jobservice.model.dto.JobDto;
import com.docker.jobservice.model.entity.Job;

public class MapUtil {

    public static JobDto toDto(Job job) {
        return JobDto.create(
                job.getId(),
                job.getDescription(),
                job.getCompany(),
                job.getSkills(),
                job.getSalary(),
                job.getIsRemote()
        );
    }

    public static Job toEntity(JobDto dto) {
        return Job.create(
                dto.getId(),
                dto.getDescription(),
                dto.getCompany(),
                dto.getSkills(),
                dto.getSalary(),
                dto.getIsRemote()
        );
    }
}
