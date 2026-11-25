package com.aihrms.backend.department.repository;

import com.aihrms.backend.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByDepartmentNameContainingIgnoreCase(String keyword);

}