package com.aihrms.backend.department.service;

import com.aihrms.backend.department.entity.Department;
import com.aihrms.backend.department.repository.DepartmentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {

        // Auto Generate Department Code
        if (department.getDepartmentCode() == null ||
                department.getDepartmentCode().isBlank()) {

            long count = departmentRepository.count() + 1;
            String code = String.format("DEP%03d", count);
            department.setDepartmentCode(code);
        }

        return departmentRepository.save(department);
    }

    @Override
    public Page<Department> getAllDepartments(int page, int size) {
        return departmentRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));
    }

    @Override
    public Department updateDepartment(Long id, Department department) {

        Department existing = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found"));

        existing.setDepartmentName(department.getDepartmentName());
        existing.setDescription(department.getDescription());

        return departmentRepository.save(existing);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> searchDepartments(String keyword) {
        return departmentRepository
                .findByDepartmentNameContainingIgnoreCase(keyword);
    }
}