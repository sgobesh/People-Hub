package com.aihrms.backend.recruitment.mapper;

import com.aihrms.backend.recruitment.dto.CandidateRequestDTO;
import com.aihrms.backend.recruitment.dto.CandidateResponseDTO;
import com.aihrms.backend.recruitment.entity.Candidate;
import com.aihrms.backend.recruitment.entity.Job;

public class CandidateMapper {

    private CandidateMapper() {
    }

    public static Candidate toEntity(
            CandidateRequestDTO dto,
            Job job) {

        Candidate candidate = new Candidate();

        candidate.setFirstName(dto.getFirstName());
        candidate.setLastName(dto.getLastName());
        candidate.setEmail(dto.getEmail());
        candidate.setPhone(dto.getPhone());
        candidate.setExperience(dto.getExperience());
        candidate.setQualification(dto.getQualification());
        candidate.setSkills(dto.getSkills());
        candidate.setResumeUrl(dto.getResumeUrl());
        candidate.setStatus(dto.getStatus());
        candidate.setJob(job);

        return candidate;
    }

    public static CandidateResponseDTO toDTO(Candidate candidate) {

        CandidateResponseDTO dto = new CandidateResponseDTO();

        dto.setId(candidate.getId());
        dto.setCandidateCode(candidate.getCandidateCode());
        dto.setFirstName(candidate.getFirstName());
        dto.setLastName(candidate.getLastName());
        dto.setEmail(candidate.getEmail());
        dto.setPhone(candidate.getPhone());
        dto.setExperience(candidate.getExperience());
        dto.setQualification(candidate.getQualification());
        dto.setSkills(candidate.getSkills());
        dto.setResumeUrl(candidate.getResumeUrl());

        dto.setJobId(candidate.getJob().getId());
        dto.setJobTitle(candidate.getJob().getJobTitle());

        dto.setStatus(candidate.getStatus());

        return dto;
    }

    public static void update(
            Candidate candidate,
            CandidateRequestDTO dto,
            Job job) {

        candidate.setFirstName(dto.getFirstName());
        candidate.setLastName(dto.getLastName());
        candidate.setEmail(dto.getEmail());
        candidate.setPhone(dto.getPhone());
        candidate.setExperience(dto.getExperience());
        candidate.setQualification(dto.getQualification());
        candidate.setSkills(dto.getSkills());
        candidate.setResumeUrl(dto.getResumeUrl());
        candidate.setStatus(dto.getStatus());
        candidate.setJob(job);
    }
}