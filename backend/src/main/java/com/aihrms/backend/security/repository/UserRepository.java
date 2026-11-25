package com.aihrms.backend.security.repository;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // =====================================================
    // Find User
    // =====================================================

    Optional<User> findByEmail(String email);

    Optional<User> findByEmployee(Employee employee);

    // =====================================================
    // Exists
    // =====================================================

    boolean existsByEmail(String email);

    boolean existsByEmployee(Employee employee);

}