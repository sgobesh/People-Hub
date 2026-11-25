package com.aihrms.backend.recruitment.repository;

import com.aihrms.backend.recruitment.entity.Job;
import com.aihrms.backend.recruitment.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    List<Job> findByJobTitleContainingIgnoreCase(String keyword);

    List<Job> findByDepartmentContainingIgnoreCase(String keyword);

    List<Job> findByStatus(JobStatus status);

    long countByStatus(JobStatus status);

}