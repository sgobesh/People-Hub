package com.aihrms.backend.profile.service;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.exception.ResourceNotFoundException;
import com.aihrms.backend.profile.dto.ChangePasswordDTO;
import com.aihrms.backend.profile.dto.ProfileResponseDTO;
import com.aihrms.backend.profile.dto.ProfileUpdateDTO;
import com.aihrms.backend.profile.mapper.ProfileMapper;
import com.aihrms.backend.security.entity.User;
import com.aihrms.backend.security.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(
            UserRepository userRepository,
            EmployeeRepository employeeRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // =====================================
    // Logged In Email
    // =====================================

    private String getLoggedInEmail() {

    Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null
            || !authentication.isAuthenticated()
            || "anonymousUser".equals(authentication.getName())) {

        throw new RuntimeException("User not authenticated");
    }

    return authentication.getName();
}

    // =====================================
    // Logged In User
    // =====================================

 private User getLoggedInUser() {

    String email = getLoggedInEmail();

    System.out.println("====================================");
    System.out.println("Logged In Email : " + email);

    System.out.println("Users in Database:");

    userRepository.findAll().forEach(user -> {
        System.out.println(
                "Email = " + user.getEmail()
                + ", Employee = "
                + (user.getEmployee() != null
                        ? user.getEmployee().getEmployeeId()
                        : "NULL")
        );
    });

    return userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new ResourceNotFoundException("User not found"));
}

    // =====================================
    // Logged In Employee
    // =====================================

    private Employee getLoggedInEmployee() {

        User user = getLoggedInUser();

        if (user.getEmployee() == null) {
            throw new ResourceNotFoundException(
                    "Employee profile is not linked to this user.");
        }

        return user.getEmployee();
    }

    // =====================================
    // Get Profile
    // =====================================

    public ProfileResponseDTO getProfile() {

        Employee employee = getLoggedInEmployee();

        return ProfileMapper.toResponseDTO(employee);
    }

    // =====================================
    // Update Profile
    // =====================================

    public ProfileResponseDTO updateProfile(ProfileUpdateDTO dto) {

        Employee employee = getLoggedInEmployee();

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setPhone(dto.getPhone());
        employee.setGender(dto.getGender());

        employeeRepository.save(employee);

        return ProfileMapper.toResponseDTO(employee);
    }

    // =====================================
    // Change Password
    // =====================================

    public void changePassword(ChangePasswordDTO dto) {

    User user = getLoggedInUser();

    if (!passwordEncoder.matches(
            dto.getCurrentPassword(),
            user.getPassword())) {

        throw new RuntimeException("Current password is incorrect");
    }

    if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {

        throw new RuntimeException(
                "New Password and Confirm Password do not match");
    }

    user.setPassword(passwordEncoder.encode(dto.getNewPassword()));

    userRepository.save(user);
}
}