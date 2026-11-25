package com.aihrms.backend.attendance.controller;

import com.aihrms.backend.attendance.dto.AttendanceRequestDTO;
import com.aihrms.backend.attendance.dto.AttendanceResponseDTO;
import com.aihrms.backend.attendance.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin("*")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }
@PutMapping("/{id}")
public ResponseEntity<AttendanceResponseDTO> updateAttendance(
        @PathVariable Long id,
        @RequestBody AttendanceRequestDTO dto) {

    return ResponseEntity.ok(
            attendanceService.updateAttendance(id, dto));
}
    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> createAttendance(
            @RequestBody AttendanceRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(attendanceService.createAttendance(dto));
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponseDTO>> getAllAttendance() {

        return ResponseEntity.ok(
                attendanceService.getAllAttendance());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> getAttendanceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService.getAttendanceById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(
            @PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.ok("Attendance deleted successfully");
    }
}