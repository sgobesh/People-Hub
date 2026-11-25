package com.aihrms.backend.recruitment.service;

import com.aihrms.backend.exception.ResourceNotFoundException;
import com.aihrms.backend.notification.enums.NotificationType;
import com.aihrms.backend.recruitment.dto.CandidateRequestDTO;
import com.aihrms.backend.recruitment.dto.CandidateResponseDTO;
import com.aihrms.backend.recruitment.entity.Candidate;
import com.aihrms.backend.recruitment.entity.CandidateStatus;
import com.aihrms.backend.recruitment.entity.Job;
import com.aihrms.backend.recruitment.mapper.CandidateMapper;
import com.aihrms.backend.recruitment.repository.CandidateRepository;
import com.aihrms.backend.recruitment.repository.JobRepository;
import com.aihrms.backend.notification.service.NotificationService;
import com.aihrms.backend.notification.enums.NotificationType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
private final JobRepository jobRepository;
private final NotificationService notificationService;
public CandidateService(
        CandidateRepository candidateRepository,
        JobRepository jobRepository,
        NotificationService notificationService) {

    this.candidateRepository = candidateRepository;
    this.jobRepository = jobRepository;
    this.notificationService = notificationService;
}

    // Create Candidate
public CandidateResponseDTO createCandidate(CandidateRequestDTO dto) {

    Job job = jobRepository.findById(dto.getJobId())
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Job not found with id : " + dto.getJobId()));

    Candidate candidate = CandidateMapper.toEntity(dto, job);

    candidate.setCandidateCode(
            "CAN-" +
                    UUID.randomUUID()
                            .toString()
                            .substring(0, 8)
                            .toUpperCase());

    if (candidate.getStatus() == null) {
        candidate.setStatus(CandidateStatus.APPLIED);
    }

    Candidate savedCandidate = candidateRepository.save(candidate);

    notificationService.createNotification(

            "New Candidate",

            savedCandidate.getFirstName()
                    + " applied for "
                    + job.getJobTitle(),

            NotificationType.CANDIDATE
    );

    return CandidateMapper.toDTO(savedCandidate);
}
    // Get All Candidates
    public List<CandidateResponseDTO> getAllCandidates() {

        return candidateRepository.findAll()
                .stream()
                .map(CandidateMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get Candidate By Id
    public CandidateResponseDTO getCandidateById(Long id) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Candidate not found with id : " + id));

        return CandidateMapper.toDTO(candidate);
    }

    // Update Candidate
   public CandidateResponseDTO updateCandidate(
        Long id,
        CandidateRequestDTO dto) {

    Candidate candidate =
            candidateRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Candidate not found with id : " + id));

    Job job =
            jobRepository.findById(dto.getJobId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Job not found with id : " + dto.getJobId()));

    CandidateMapper.update(candidate, dto, job);

    Candidate updatedCandidate = candidateRepository.save(candidate);

    notificationService.createNotification(

            "Candidate Updated",

            updatedCandidate.getFirstName()
                    + " status changed to "
                    + updatedCandidate.getStatus(),

            NotificationType.CANDIDATE
    );

    return CandidateMapper.toDTO(updatedCandidate);
}
    // Delete Candidate
    public void deleteCandidate(Long id) {

        Candidate candidate =
                candidateRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Candidate not found with id : " + id));

        candidateRepository.delete(candidate);
    }

    // Search Candidate
    public List<CandidateResponseDTO> searchCandidates(String keyword) {

        return candidateRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        keyword,
                        keyword)
                .stream()
                .map(CandidateMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Shortlist
    public CandidateResponseDTO shortlistCandidate(Long id) {

    Candidate candidate =
            candidateRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Candidate not found"));

    candidate.setStatus(CandidateStatus.SHORTLISTED);

    Candidate updated = candidateRepository.save(candidate);

    notificationService.createNotification(

            "Candidate Shortlisted",

            updated.getFirstName()
                    + " has been shortlisted.",

            NotificationType.CANDIDATE
    );

    return CandidateMapper.toDTO(updated);
}

    // Interview
   public CandidateResponseDTO interviewCandidate(Long id) {

    Candidate candidate =
            candidateRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Candidate not found"));

    candidate.setStatus(CandidateStatus.INTERVIEW);

    Candidate updated = candidateRepository.save(candidate);

    notificationService.createNotification(

            "Interview Scheduled",

            updated.getFirstName()
                    + " moved to Interview stage.",

            NotificationType.CANDIDATE
    );

    return CandidateMapper.toDTO(updated);
}
    // Select
    public CandidateResponseDTO selectCandidate(Long id) {

    Candidate candidate =
            candidateRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Candidate not found"));

    candidate.setStatus(CandidateStatus.SELECTED);

    Candidate updated = candidateRepository.save(candidate);

    notificationService.createNotification(

            "Candidate Selected",

            updated.getFirstName()
                    + " has been selected.",

            NotificationType.CANDIDATE
    );

    return CandidateMapper.toDTO(updated);
}
    // Reject
   public CandidateResponseDTO rejectCandidate(Long id) {

    Candidate candidate =
            candidateRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Candidate not found"));

    candidate.setStatus(CandidateStatus.REJECTED);

    Candidate updated = candidateRepository.save(candidate);

    notificationService.createNotification(

            "Candidate Rejected",

            updated.getFirstName()
                    + " has been rejected.",

            NotificationType.CANDIDATE
    );

    return CandidateMapper.toDTO(updated);
}
}