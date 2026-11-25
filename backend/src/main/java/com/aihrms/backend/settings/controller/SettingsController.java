package com.aihrms.backend.settings.controller;

import com.aihrms.backend.settings.dto.CompanyRequestDTO;
import com.aihrms.backend.settings.dto.CompanyResponseDTO;
import com.aihrms.backend.settings.service.SettingsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin("*")
public class SettingsController {

    private final SettingsService settingsService;

    public SettingsController(SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    // Get Company Details
    @GetMapping("/company")
    public ResponseEntity<CompanyResponseDTO> getCompany() {

        return ResponseEntity.ok(
                settingsService.getCompany()
        );
    }

    // Save / Update Company
    @PutMapping("/company")
    public ResponseEntity<CompanyResponseDTO> saveCompany(
            @Valid @RequestBody CompanyRequestDTO dto) {

        return ResponseEntity.ok(
                settingsService.saveCompany(dto)
        );
    }
}