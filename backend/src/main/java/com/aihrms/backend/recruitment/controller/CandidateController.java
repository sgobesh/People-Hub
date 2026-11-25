package com.aihrms.backend.recruitment.controller;

import com.aihrms.backend.recruitment.dto.CandidateRequestDTO;
import com.aihrms.backend.recruitment.dto.CandidateResponseDTO;
import com.aihrms.backend.recruitment.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Apply Candidate
    @PostMapping
    public ResponseEntity<CandidateResponseDTO> createCandidate(
            @Valid @RequestBody CandidateRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(candidateService.createCandidate(dto));
    }

    // Get All Candidates
    @GetMapping
    public ResponseEntity<List<CandidateResponseDTO>> getAllCandidates() {

        return ResponseEntity.ok(
                candidateService.getAllCandidates()
        );
    }

    // Get Candidate By Id
    @GetMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> getCandidateById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                candidateService.getCandidateById(id)
        );
    }

    // Update Candidate
    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponseDTO> updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidateRequestDTO dto) {

        return ResponseEntity.ok(
                candidateService.updateCandidate(id, dto)
        );
    }

    // Delete Candidate
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCandidate(
            @PathVariable Long id) {

        candidateService.deleteCandidate(id);

        return ResponseEntity.ok("Candidate deleted successfully.");
    }

    // Shortlist Candidate
    @PutMapping("/{id}/shortlist")
    public ResponseEntity<CandidateResponseDTO> shortlistCandidate(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                candidateService.shortlistCandidate(id)
        );
    }

    // Select Candidate
    @PutMapping("/{id}/select")
    public ResponseEntity<CandidateResponseDTO> selectCandidate(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                candidateService.selectCandidate(id)
        );
    }

    // Reject Candidate
    @PutMapping("/{id}/reject")
    public ResponseEntity<CandidateResponseDTO> rejectCandidate(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                candidateService.rejectCandidate(id)
        );
    }
}