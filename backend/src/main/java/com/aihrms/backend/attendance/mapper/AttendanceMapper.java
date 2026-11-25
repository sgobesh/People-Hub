package com.aihrms.backend.attendance.mapper;

import com.aihrms.backend.attendance.dto.AttendanceRequestDTO;
import com.aihrms.backend.attendance.dto.AttendanceResponseDTO;
import com.aihrms.backend.attendance.entity.Attendance;
import com.aihrms.backend.employee.entity.Employee;

public class AttendanceMapper {

    public static Attendance toEntity(AttendanceRequestDTO dto) {

        Attendance attendance = new Attendance();

        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setCheckIn(dto.getCheckIn());
        attendance.setCheckOut(dto.getCheckOut());
        attendance.setStatus(dto.getStatus());

        if (dto.getEmployeeId() != null) {
            Employee employee = new Employee();
            employee.setId(dto.getEmployeeId());
            attendance.setEmployee(employee);
        }

        return attendance;
    }

    public static AttendanceResponseDTO toResponseDTO(Attendance attendance) {

        AttendanceResponseDTO dto = new AttendanceResponseDTO();

        dto.setId(attendance.getId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setCheckIn(attendance.getCheckIn());
        dto.setCheckOut(attendance.getCheckOut());
        dto.setStatus(attendance.getStatus());

        if (attendance.getEmployee() != null) {

            dto.setEmployeeId(attendance.getEmployee().getId());

            dto.setEmployeeName(
                    attendance.getEmployee().getFirstName()
                            + " "
                            + attendance.getEmployee().getLastName()
            );
        }

        return dto;
    }
}