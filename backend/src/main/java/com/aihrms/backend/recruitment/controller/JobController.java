package com.aihrms.backend.recruitment.controller;

import com.aihrms.backend.recruitment.dto.JobRequestDTO;
import com.aihrms.backend.recruitment.dto.JobResponseDTO;
import com.aihrms.backend.recruitment.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // Create Job
    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(
            @Valid @RequestBody JobRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobService.createJob(dto));
    }

    // Get All Jobs
    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {

        return ResponseEntity.ok(
                jobService.getAllJobs()
        );
    }

    // Get Job By Id
    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                jobService.getJobById(id)
        );
    }

    // Update Job
    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequestDTO dto) {

        return ResponseEntity.ok(
                jobService.updateJob(id, dto)
        );
    }

    // Delete Job
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(
            @PathVariable Long id) {

        jobService.deleteJob(id);

        return ResponseEntity.ok("Job deleted successfully.");
    }
}