package com.aihrms.backend.attendance.service;

import com.aihrms.backend.attendance.dto.AttendanceRequestDTO;
import com.aihrms.backend.attendance.dto.AttendanceResponseDTO;
import com.aihrms.backend.attendance.entity.Attendance;
import com.aihrms.backend.attendance.mapper.AttendanceMapper;
import com.aihrms.backend.attendance.repository.AttendanceRepository;
import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            EmployeeRepository employeeRepository) {

        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    // Create Attendance
    public AttendanceResponseDTO createAttendance(AttendanceRequestDTO dto) {

        Attendance attendance = AttendanceMapper.toEntity(dto);

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id : "
                                        + dto.getEmployeeId()));

        attendance.setEmployee(employee);

        Attendance saved = attendanceRepository.save(attendance);

        return AttendanceMapper.toResponseDTO(saved);
    }

    // Get All Attendance
    public List<AttendanceResponseDTO> getAllAttendance() {

        return attendanceRepository.findAll()
                .stream()
                .map(AttendanceMapper::toResponseDTO)
                .toList();
    }

    // Get Attendance By ID
    public AttendanceResponseDTO getAttendanceById(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with id : " + id));

        return AttendanceMapper.toResponseDTO(attendance);
    }
public AttendanceResponseDTO updateAttendance(
        Long id,
        AttendanceRequestDTO dto) {

    Attendance attendance = attendanceRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Attendance not found with id : " + id));

    Employee employee = employeeRepository.findById(dto.getEmployeeId())
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Employee not found with id : "
                                    + dto.getEmployeeId()));

    attendance.setEmployee(employee);
    attendance.setAttendanceDate(dto.getAttendanceDate());
    attendance.setCheckIn(dto.getCheckIn());
    attendance.setCheckOut(dto.getCheckOut());
    attendance.setStatus(dto.getStatus());

    Attendance updated = attendanceRepository.save(attendance);

    return AttendanceMapper.toResponseDTO(updated);
}
    // Delete Attendance
    public void deleteAttendance(Long id) {

        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Attendance not found with id : " + id));

        attendanceRepository.delete(attendance);
    }
}