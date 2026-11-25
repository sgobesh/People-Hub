package com.aihrms.backend.attendance.repository;

import com.aihrms.backend.attendance.entity.Attendance;
import com.aihrms.backend.attendance.entity.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployeeId(Long employeeId);

    List<Attendance> findByAttendanceDate(LocalDate attendanceDate);

    long countByAttendanceDate(LocalDate date);

    long countByAttendanceDateAndStatus(
            LocalDate date,
            AttendanceStatus status
    );
    boolean existsByEmployeeId(Long employeeId);
}