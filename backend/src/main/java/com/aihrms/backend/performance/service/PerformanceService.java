package com.aihrms.backend.performance.service;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.performance.dto.PerformanceRequestDTO;
import com.aihrms.backend.performance.entity.Performance;
import com.aihrms.backend.performance.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final EmployeeRepository employeeRepository;

    public PerformanceService(
            PerformanceRepository performanceRepository,
            EmployeeRepository employeeRepository) {

        this.performanceRepository = performanceRepository;
        this.employeeRepository = employeeRepository;
    }

    public Performance createPerformance(PerformanceRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        Performance performance = new Performance();

        performance.setEmployee(employee);
        performance.setRating(dto.getRating());
        performance.setGoals(dto.getGoals());
        performance.setComments(dto.getComments());
        performance.setReviewDate(dto.getReviewDate());
        performance.setReviewer(dto.getReviewer());

        return performanceRepository.save(performance);
    }

    public List<Performance> getAllPerformance() {
        return performanceRepository.findAll();
    }

    public List<Performance> getPerformanceByEmployee(Long employeeId) {
        return performanceRepository.findByEmployeeId(employeeId);
    }
}