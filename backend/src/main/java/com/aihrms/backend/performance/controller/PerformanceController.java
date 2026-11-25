package com.aihrms.backend.performance.controller;

import com.aihrms.backend.performance.dto.PerformanceRequestDTO;
import com.aihrms.backend.performance.entity.Performance;
import com.aihrms.backend.performance.service.PerformanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performance")
@CrossOrigin("*")
public class PerformanceController {

    private final PerformanceService performanceService;

    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @PostMapping
    public Performance createPerformance(
            @RequestBody PerformanceRequestDTO dto) {

        return performanceService.createPerformance(dto);
    }

    @GetMapping
    public List<Performance> getAllPerformance() {
        return performanceService.getAllPerformance();
    }

    @GetMapping("/employee/{id}")
    public List<Performance> getEmployeePerformance(
            @PathVariable Long id) {

        return performanceService.getPerformanceByEmployee(id);
    }
}