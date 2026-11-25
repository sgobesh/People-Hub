package com.aihrms.backend.recruitment.mapper;

import com.aihrms.backend.recruitment.dto.JobRequestDTO;
import com.aihrms.backend.recruitment.dto.JobResponseDTO;
import com.aihrms.backend.recruitment.entity.Job;

public class JobMapper {

    private JobMapper() {
    }

    public static Job toEntity(JobRequestDTO dto) {

        Job job = new Job();

        job.setJobTitle(dto.getJobTitle());
        job.setDepartment(dto.getDepartment());
        job.setLocation(dto.getLocation());
        job.setDescription(dto.getDescription());
        job.setVacancies(dto.getVacancies());
        job.setSalary(dto.getSalary());
        job.setStatus(dto.getStatus());

        return job;
    }

    public static JobResponseDTO toDTO(Job job) {

        JobResponseDTO dto = new JobResponseDTO();

        dto.setId(job.getId());
        dto.setJobCode(job.getJobCode());
        dto.setJobTitle(job.getJobTitle());
        dto.setDepartment(job.getDepartment());
        dto.setLocation(job.getLocation());
        dto.setDescription(job.getDescription());
        dto.setVacancies(job.getVacancies());
        dto.setSalary(job.getSalary());
        dto.setStatus(job.getStatus());

        return dto;
    }

    public static void update(Job job, JobRequestDTO dto) {

        job.setJobTitle(dto.getJobTitle());
        job.setDepartment(dto.getDepartment());
        job.setLocation(dto.getLocation());
        job.setDescription(dto.getDescription());
        job.setVacancies(dto.getVacancies());
        job.setSalary(dto.getSalary());
        job.setStatus(dto.getStatus());
    }
}