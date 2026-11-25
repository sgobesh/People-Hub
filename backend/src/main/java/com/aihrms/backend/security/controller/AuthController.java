package com.aihrms.backend.security.controller;


import com.aihrms.backend.security.dto.AuthResponse;
import com.aihrms.backend.security.dto.LoginRequest;
import com.aihrms.backend.security.dto.RegisterRequest;
import com.aihrms.backend.security.dto.ForgotPasswordRequest;
import com.aihrms.backend.security.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;



    public AuthController(AuthService authService) {

        this.authService = authService;

    }



    // ==============================
    // Register API
    // ==============================

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {


        return ResponseEntity.ok(
                authService.register(request)
        );

    }





    // ==============================
    // Forgot Password API
    // ==============================

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestBody ForgotPasswordRequest request) {


        authService.resetPassword(request);


        return ResponseEntity.ok(
                "Password updated successfully"
        );

    }






    // ==============================
    // Login API
    // ==============================

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {


        return ResponseEntity.ok(
                authService.login(request)
        );

    }


}