package com.aihrms.backend.dashboard.service;

import com.aihrms.backend.attendance.entity.AttendanceStatus;
import com.aihrms.backend.attendance.repository.AttendanceRepository;
import com.aihrms.backend.dashboard.dto.DashboardResponseDTO;
import com.aihrms.backend.department.repository.DepartmentRepository;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.leave.entity.LeaveStatus;
import com.aihrms.backend.leave.repository.LeaveRepository;
import com.aihrms.backend.payroll.repository.PayrollRepository;
import com.aihrms.backend.performance.repository.PerformanceRepository;
import com.aihrms.backend.recruitment.repository.JobRepository;
import com.aihrms.backend.recruitment.repository.CandidateRepository;
import com.aihrms.backend.recruitment.entity.JobStatus;
import com.aihrms.backend.recruitment.entity.CandidateStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final LeaveRepository leaveRepository;
    private final PayrollRepository payrollRepository;
    private final PerformanceRepository performanceRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;

    public DashboardService(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            AttendanceRepository attendanceRepository,
            LeaveRepository leaveRepository,
            PayrollRepository payrollRepository,
            PerformanceRepository performanceRepository,
            JobRepository jobRepository,
            CandidateRepository candidateRepository) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.leaveRepository = leaveRepository;
        this.payrollRepository = payrollRepository;
        this.performanceRepository = performanceRepository;
        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
    }

    public DashboardResponseDTO getDashboard() {

        DashboardResponseDTO dto = new DashboardResponseDTO();

        LocalDate today = LocalDate.now();

        dto.setTotalEmployees(employeeRepository.count());

        dto.setTotalDepartments(departmentRepository.count());

        dto.setTotalAttendanceToday(
                attendanceRepository.countByAttendanceDate(today));

        dto.setPresentToday(
                attendanceRepository.countByAttendanceDateAndStatus(
                        today,
                        AttendanceStatus.PRESENT));

        dto.setAbsentToday(
                attendanceRepository.countByAttendanceDateAndStatus(
                        today,
                        AttendanceStatus.ABSENT));

        dto.setEmployeesOnLeave(
        leaveRepository.countByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                "Approved",
                today,
                today));
dto.setPendingLeaves(
        leaveRepository.countByStatus("Pending"));

      dto.setApprovedLeaves(
        leaveRepository.countByStatus("Approved"));

        dto.setTotalPayrolls(payrollRepository.count());

        dto.setTotalPerformanceReviews(performanceRepository.count());

        dto.setTotalJobs(jobRepository.count());

        dto.setOpenJobs(
        jobRepository.countByStatus(JobStatus.OPEN));

        dto.setTotalCandidates(candidateRepository.count());

        dto.setSelectedCandidates(
        candidateRepository.countByStatus(CandidateStatus.SELECTED));

dto.setRejectedCandidates(
        candidateRepository.countByStatus(CandidateStatus.REJECTED));

        return dto;
    }
}