package com.aihrms.backend.employee.repository;

import com.aihrms.backend.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
@Query("SELECT MAX(e.id) FROM Employee e")
Long findMaxId();
    List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmployeeIdContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String firstName,
            String lastName,
            String employeeId,
            String email
    );

    Optional<Employee> findByEmail(String email);

}