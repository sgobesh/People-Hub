package com.aihrms.backend.employee.mapper;

import com.aihrms.backend.department.entity.Department;
import com.aihrms.backend.employee.dto.EmployeeRequestDTO;
import com.aihrms.backend.employee.dto.EmployeeResponseDTO;
import com.aihrms.backend.employee.entity.Employee;

public class EmployeeMapper {

    // Convert Request DTO to Entity
    public static Employee toEntity(EmployeeRequestDTO dto) {

        Employee employee = new Employee();

       
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());

        employee.setJoiningDate(dto.getJoiningDate());
        employee.setGender(dto.getGender());

        if (dto.getDepartmentId() != null) {
            Department department = new Department();
            department.setId(dto.getDepartmentId());
            employee.setDepartment(department);
        }

        return employee;
    }

    // Convert Entity to Response DTO
    public static EmployeeResponseDTO toResponseDTO(Employee employee) {

        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(employee.getId());
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDesignation(employee.getDesignation());
        dto.setSalary(employee.getSalary());

        dto.setJoiningDate(employee.getJoiningDate());
        dto.setGender(employee.getGender());

        if (employee.getDepartment() != null) {
          dto.setDepartmentId(employee.getDepartment().getId());
            dto.setDepartmentName(employee.getDepartment().getDepartmentName());
        }

        return dto;
    }
}