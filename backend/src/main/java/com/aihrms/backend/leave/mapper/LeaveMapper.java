package com.aihrms.backend.leave.mapper;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.leave.dto.LeaveRequestDTO;
import com.aihrms.backend.leave.dto.LeaveResponseDTO;
import com.aihrms.backend.leave.entity.Leave;

public class LeaveMapper {

    // Convert Request DTO to Entity
    public static Leave toEntity(LeaveRequestDTO dto) {

        Leave leave = new Leave();

        // Set Employee
        Employee employee = new Employee();
        employee.setId(dto.getEmployeeId());
        leave.setEmployee(employee);

        leave.setLeaveType(dto.getLeaveType());
        leave.setStartDate(dto.getStartDate());
        leave.setEndDate(dto.getEndDate());
        leave.setReason(dto.getReason());
        leave.setRemarks(dto.getRemarks());

        return leave;
    }

    // Convert Entity to Response DTO
    public static LeaveResponseDTO toDTO(Leave leave) {

        LeaveResponseDTO dto = new LeaveResponseDTO();

        dto.setId(leave.getId());
        dto.setLeaveCode(leave.getLeaveCode());

      if (leave.getEmployee() != null) {

    dto.setEmployeeId(leave.getEmployee().getId());

    String firstName = leave.getEmployee().getFirstName() != null
            ? leave.getEmployee().getFirstName()
            : "";

    String lastName = leave.getEmployee().getLastName() != null
            ? leave.getEmployee().getLastName()
            : "";

    dto.setEmployeeName((firstName + " " + lastName).trim());
}

        dto.setLeaveType(leave.getLeaveType());
        dto.setStartDate(leave.getStartDate());
        dto.setEndDate(leave.getEndDate());
        dto.setNumberOfDays(leave.getNumberOfDays());
        dto.setReason(leave.getReason());
        dto.setStatus(leave.getStatus());
        dto.setAppliedDate(leave.getAppliedDate());
        dto.setApprovedBy(leave.getApprovedBy());
        dto.setRemarks(leave.getRemarks()); 

        return dto;
    }
}