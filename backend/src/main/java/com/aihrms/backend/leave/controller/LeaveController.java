package com.aihrms.backend.leave.controller;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.leave.dto.LeaveRequestDTO;
import com.aihrms.backend.leave.dto.LeaveResponseDTO;
import com.aihrms.backend.leave.entity.Leave;
import com.aihrms.backend.leave.mapper.LeaveMapper;
import com.aihrms.backend.leave.service.LeaveService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "*")
public class LeaveController {

    private final LeaveService leaveService;
    private final EmployeeRepository employeeRepository;

    public LeaveController(
            LeaveService leaveService,
            EmployeeRepository employeeRepository
    ) {
        this.leaveService = leaveService;
        this.employeeRepository = employeeRepository;
    }

    // Apply Leave
    @PostMapping
    public ResponseEntity<LeaveResponseDTO> saveLeave(
            @RequestBody LeaveRequestDTO dto) {

        Leave leave = LeaveMapper.toEntity(dto);

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leave.setEmployee(employee);

        Leave savedLeave = leaveService.saveLeave(leave);

        return ResponseEntity.ok(LeaveMapper.toDTO(savedLeave));
    }

    // Get All Leaves
    @GetMapping
    public ResponseEntity<Page<Leave>> getAllLeaves(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(
                leaveService.getAllLeaves(page, size)
        );
    }

    // Get Leave By Id
    @GetMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> getLeaveById(
            @PathVariable Long id) {

        Leave leave = leaveService.getLeaveById(id);

        return ResponseEntity.ok(
                LeaveMapper.toDTO(leave)
        );
    }

    // Update Leave
    @PutMapping("/{id}")
    public ResponseEntity<LeaveResponseDTO> updateLeave(
            @PathVariable Long id,
            @RequestBody LeaveRequestDTO dto) {

        Leave leave = LeaveMapper.toEntity(dto);

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        leave.setEmployee(employee);

        Leave updatedLeave = leaveService.updateLeave(id, leave);

        return ResponseEntity.ok(
                LeaveMapper.toDTO(updatedLeave)
        );
    }

    // Delete Leave
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLeave(
            @PathVariable Long id) {

        leaveService.deleteLeave(id);

        return ResponseEntity.ok("Leave deleted successfully.");
    }

    // Search Leaves
    @GetMapping("/search")
    public ResponseEntity<List<Leave>> searchLeaves(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                leaveService.searchLeaves(keyword)
        );
    }

    // Approve Leave
    @PutMapping("/{id}/approve")
    public ResponseEntity<LeaveResponseDTO> approveLeave(
            @PathVariable Long id) {

        Leave leave = leaveService.approveLeave(id);

        return ResponseEntity.ok(
                LeaveMapper.toDTO(leave)
        );
    }

    // Reject Leave
    @PutMapping("/{id}/reject")
    public ResponseEntity<LeaveResponseDTO> rejectLeave(
            @PathVariable Long id) {

        Leave leave = leaveService.rejectLeave(id);

        return ResponseEntity.ok(
                LeaveMapper.toDTO(leave)
        );
    }
}