package com.aihrms.backend.profile.mapper;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.profile.dto.ProfileResponseDTO;

public class ProfileMapper {

    private ProfileMapper() {
    }

    public static ProfileResponseDTO toResponseDTO(Employee employee) {

        if (employee == null) {
            return null;
        }

        ProfileResponseDTO dto = new ProfileResponseDTO();

        dto.setEmployeeId(employee.getEmployeeId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDesignation(employee.getDesignation());
        dto.setGender(employee.getGender());

        if (employee.getDepartment() != null) {
            dto.setDepartment(
                    employee.getDepartment().getDepartmentName()
            );
        }

        return dto;
    }
}