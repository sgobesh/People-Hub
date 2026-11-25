package com.aihrms.backend.recruitment.service;

import com.aihrms.backend.exception.ResourceNotFoundException;
import com.aihrms.backend.recruitment.dto.JobRequestDTO;
import com.aihrms.backend.recruitment.dto.JobResponseDTO;
import com.aihrms.backend.recruitment.entity.Job;
import com.aihrms.backend.recruitment.mapper.JobMapper;
import com.aihrms.backend.recruitment.repository.JobRepository;
import com.aihrms.backend.notification.enums.NotificationType;
import com.aihrms.backend.notification.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JobService {

    private final JobRepository jobRepository;
private final NotificationService notificationService;
public JobService(
        JobRepository jobRepository,
        NotificationService notificationService) {

    this.jobRepository = jobRepository;
    this.notificationService = notificationService;
}

    // Create Job
    public JobResponseDTO createJob(JobRequestDTO dto) {

        Job job = JobMapper.toEntity(dto);

        job.setJobCode(
                "JOB-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase());

        Job saved = jobRepository.save(job);
notificationService.createNotification(

        "New Job Posted",

        saved.getJobTitle() + " has been published.",

        NotificationType.JOB
);
        return JobMapper.toDTO(saved);
    }

    // Get All Jobs
    public List<JobResponseDTO> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get Job By Id
    public JobResponseDTO getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id : " + id));

        return JobMapper.toDTO(job);
    }

    // Update Job
    public JobResponseDTO updateJob(
            Long id,
            JobRequestDTO dto) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id : " + id));

        JobMapper.update(job, dto);

        Job updated = jobRepository.save(job);
notificationService.createNotification(

        "Job Updated",

        updated.getJobTitle() + " has been updated.",

        NotificationType.JOB
);
        return JobMapper.toDTO(updated);
    }

    // Delete Job
    public void deleteJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id : " + id));

        jobRepository.delete(job);
        notificationService.createNotification(

        "Job Deleted",

        job.getJobTitle() + " has been deleted.",

        NotificationType.JOB
);jobRepository.delete(job);
    }

    // Search Jobs
    public List<JobResponseDTO> searchJobs(String keyword) {

        return jobRepository
                .findByJobTitleContainingIgnoreCase(keyword)
                .stream()
                .map(JobMapper::toDTO)
                .collect(Collectors.toList());
    }
}