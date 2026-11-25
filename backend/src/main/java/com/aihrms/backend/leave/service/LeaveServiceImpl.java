package com.aihrms.backend.leave.service;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.leave.entity.Leave;
import com.aihrms.backend.leave.repository.LeaveRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveServiceImpl(
            LeaveRepository leaveRepository,
            EmployeeRepository employeeRepository) {

        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Leave saveLeave(Leave leave) {

        // Generate Leave Code
        if (leave.getLeaveCode() == null || leave.getLeaveCode().isEmpty()) {

            long count = leaveRepository.count() + 1;

            leave.setLeaveCode(String.format("LV%03d", count));
        }

        // Validate Employee
        Employee employee = employeeRepository.findById(
                leave.getEmployee().getId()
        ).orElseThrow(() ->
                new RuntimeException("Employee not found"));

        leave.setEmployee(employee);

        // Default Status
        leave.setStatus("Pending");

        // Applied Date
        leave.setAppliedDate(LocalDate.now());

        // Calculate Number of Days
        if (leave.getStartDate() != null && leave.getEndDate() != null) {

            long days = ChronoUnit.DAYS.between(
                    leave.getStartDate(),
                    leave.getEndDate()
            ) + 1;

            leave.setNumberOfDays((int) days);
        }

        return leaveRepository.save(leave);
    }

    @Override
    public Page<Leave> getAllLeaves(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return leaveRepository.findAll(pageable);
    }

    @Override
    public Leave getLeaveById(Long id) {

        return leaveRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave not found"));
    }

    @Override
    public Leave updateLeave(Long id, Leave leave) {

        Leave existing = getLeaveById(id);

        Employee employee = employeeRepository.findById(
                leave.getEmployee().getId()
        ).orElseThrow(() ->
                new RuntimeException("Employee not found"));

        existing.setEmployee(employee);
        existing.setLeaveType(leave.getLeaveType());
        existing.setStartDate(leave.getStartDate());
        existing.setEndDate(leave.getEndDate());
        existing.setReason(leave.getReason());
        existing.setRemarks(leave.getRemarks());

        if (leave.getStartDate() != null && leave.getEndDate() != null) {

            long days = ChronoUnit.DAYS.between(
                    leave.getStartDate(),
                    leave.getEndDate()
            ) + 1;

            existing.setNumberOfDays((int) days);
        }

        return leaveRepository.save(existing);
    }

    @Override
    public void deleteLeave(Long id) {

        leaveRepository.deleteById(id);
    }

    @Override
    public List<Leave> searchLeaves(String keyword) {

        return leaveRepository.findAll().stream()
                .filter(l ->

                        (l.getLeaveCode() != null &&
                                l.getLeaveCode().toLowerCase().contains(keyword.toLowerCase()))

                                ||

                                (l.getLeaveType() != null &&
                                        l.getLeaveType().toLowerCase().contains(keyword.toLowerCase()))

                                ||

                                (l.getStatus() != null &&
                                        l.getStatus().toLowerCase().contains(keyword.toLowerCase()))

                                ||

                                (l.getEmployee() != null &&
                                        (
                                                l.getEmployee().getFirstName().toLowerCase().contains(keyword.toLowerCase())
                                                        ||
                                                l.getEmployee().getLastName().toLowerCase().contains(keyword.toLowerCase())
                                        ))

                )
                .toList();
    }

   @Override
public Leave approveLeave(Long id) {

    Leave leave = getLeaveById(id);

    leave.setStatus("Approved");

    // Later replace "Admin" with logged-in username
    leave.setApprovedBy("Admin");

    return leaveRepository.save(leave);
}
@Override
public Leave rejectLeave(Long id) {

    Leave leave = getLeaveById(id);

    leave.setStatus("Rejected");

    leave.setApprovedBy("Admin");

    return leaveRepository.save(leave);
}
}