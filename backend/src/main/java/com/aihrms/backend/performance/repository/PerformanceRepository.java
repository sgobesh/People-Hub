package com.aihrms.backend.performance.repository;

import com.aihrms.backend.performance.entity.Performance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    List<Performance> findByEmployeeId(Long employeeId);
boolean existsByEmployeeId(Long employeeId);
}