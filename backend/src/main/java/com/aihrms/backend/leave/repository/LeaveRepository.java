package com.aihrms.backend.leave.repository;

import com.aihrms.backend.leave.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {

    List<Leave> findByEmployeeId(Long employeeId);

    long countByStatus(String status);

    long countByStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            String status,
            LocalDate startDate,
            LocalDate endDate
    );

    // NEW
    boolean existsByEmployeeId(Long employeeId);
}