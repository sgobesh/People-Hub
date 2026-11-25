package com.aihrms.backend.department.service;

import com.aihrms.backend.department.entity.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    Page<Department> getAllDepartments(int page, int size);

    Department getDepartmentById(Long id);

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);

    List<Department> searchDepartments(String keyword);
}