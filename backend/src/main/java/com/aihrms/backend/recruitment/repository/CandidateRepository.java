package com.aihrms.backend.recruitment.repository;

import com.aihrms.backend.recruitment.entity.Candidate;
import com.aihrms.backend.recruitment.entity.CandidateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // Email Validation
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    // Search
    List<Candidate> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );

    List<Candidate> findByEmailContainingIgnoreCase(String email);

    List<Candidate> findByPhoneContaining(String phone);

    List<Candidate> findByStatus(CandidateStatus status);

    // Dashboard
    long countByStatus(CandidateStatus status);
}