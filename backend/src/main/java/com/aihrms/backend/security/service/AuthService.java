package com.aihrms.backend.security.service;


import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.repository.EmployeeRepository;

import com.aihrms.backend.security.dto.AuthResponse;
import com.aihrms.backend.security.dto.LoginRequest;
import com.aihrms.backend.security.dto.RegisterRequest;
import com.aihrms.backend.security.dto.ForgotPasswordRequest;

import com.aihrms.backend.security.entity.User;

import com.aihrms.backend.security.jwt.JwtService;
import com.aihrms.backend.security.repository.UserRepository;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;



@Service
public class AuthService {


    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final EmployeeRepository employeeRepository;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;



    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            EmployeeRepository employeeRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder) {


        this.authenticationManager = authenticationManager;

        this.userRepository = userRepository;

        this.employeeRepository = employeeRepository;

        this.jwtService = jwtService;

        this.passwordEncoder = passwordEncoder;

    }





    // =====================================================
    // Forgot Password / Reset Password
    // =====================================================

    public void resetPassword(
            ForgotPasswordRequest request
    ) {


        User user =
                userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "Email not found"
                        )
                );



        user.setPassword(
                passwordEncoder.encode(
                        request.getNewPassword()
                )
        );



        userRepository.save(user);

    }





    // =====================================================
    // Register
    // =====================================================

    public String register(RegisterRequest request) {


        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException(
                    "Email already exists."
            );

        }



        User user = new User();


        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        user.setRole(request.getRole());



        // Link Employee (Optional)

        if (request.getEmployeeId() != null) {


            Employee employee =
                    employeeRepository
                    .findById(request.getEmployeeId())
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Employee not found."
                            )
                    );



            if (userRepository.existsByEmployee(employee)) {

                throw new RuntimeException(
                        "This employee already has a user account."
                );

            }



            user.setEmployee(employee);

        }



        userRepository.save(user);



        return "User registered successfully.";

    }





    // =====================================================
    // Login
    // =====================================================

    public AuthResponse login(LoginRequest request) {



        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );



        User user =
                userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found."
                        )
                );



        String token =
                jwtService.generateToken(
                        user.getEmail()
                );



        return new AuthResponse(

                token,

                user.getEmail(),

                user.getRole().name(),

                "Login successful."

        );

    }


}