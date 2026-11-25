package com.aihrms.backend.payroll.repository;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.payroll.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

    List<Payroll> findByEmployee(Employee employee);

    List<Payroll> findByEmployeeId(Long employeeId);

    List<Payroll> findByMonthAndYear(String month, Integer year);

    boolean existsByEmployeeIdAndMonthAndYear(
            Long employeeId,
            String month,
            Integer year
    );
    boolean existsByEmployeeId(Long employeeId);
}