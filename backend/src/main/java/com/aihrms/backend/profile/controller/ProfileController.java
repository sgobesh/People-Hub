package com.aihrms.backend.profile.controller;

import com.aihrms.backend.profile.dto.ChangePasswordDTO;
import com.aihrms.backend.profile.dto.ProfileResponseDTO;
import com.aihrms.backend.profile.dto.ProfileUpdateDTO;
import com.aihrms.backend.profile.service.ProfileService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // ===========================
    // Get Logged-in Profile
    // ===========================

    @GetMapping
    public ResponseEntity<ProfileResponseDTO> getProfile() {

        return ResponseEntity.ok(
                profileService.getProfile()
        );
    }

    // ===========================
    // Update Profile
    // ===========================

    @PutMapping
    public ResponseEntity<ProfileResponseDTO> updateProfile(
            @Valid @RequestBody ProfileUpdateDTO dto) {

        return ResponseEntity.ok(
                profileService.updateProfile(dto)
        );
    }

    // ===========================
    // Change Password
    // ===========================

    @PutMapping("/change-password")
    public ResponseEntity<String> changePassword(
            @Valid @RequestBody ChangePasswordDTO dto) {

        profileService.changePassword(dto);

        return ResponseEntity.ok(
                "Password changed successfully."
        );
    }
}