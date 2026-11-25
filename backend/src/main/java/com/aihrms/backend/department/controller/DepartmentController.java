package com.aihrms.backend.department.controller;

import com.aihrms.backend.department.entity.Department;
import com.aihrms.backend.department.service.DepartmentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create Department
    @PostMapping
    public ResponseEntity<Department> saveDepartment(
            @RequestBody Department department) {

        Department savedDepartment =
                departmentService.saveDepartment(department);

        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    // Get All Departments (Pagination)
    @GetMapping
    public ResponseEntity<Page<Department>> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                departmentService.getAllDepartments(page, size));
    }

    // Get Department By ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                departmentService.getDepartmentById(id));
    }

    // Update Department
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        return ResponseEntity.ok(
                departmentService.updateDepartment(id, department));
    }

    // Delete Department
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.ok("Department deleted successfully.");
    }

    // Search Department
    @GetMapping("/search")
    public ResponseEntity<List<Department>> searchDepartments(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                departmentService.searchDepartments(keyword));
    }
}