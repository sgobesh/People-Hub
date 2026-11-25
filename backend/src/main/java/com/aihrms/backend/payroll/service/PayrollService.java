package com.aihrms.backend.payroll.service;

import com.aihrms.backend.employee.entity.Employee;
import com.aihrms.backend.employee.repository.EmployeeRepository;
import com.aihrms.backend.exception.ResourceNotFoundException;
import com.aihrms.backend.payroll.dto.PayrollRequestDTO;
import com.aihrms.backend.payroll.dto.PayrollResponseDTO;
import com.aihrms.backend.payroll.entity.Payroll;
import com.aihrms.backend.payroll.entity.PayrollStatus;
import com.aihrms.backend.payroll.mapper.PayrollMapper;
import com.aihrms.backend.payroll.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeRepository employeeRepository;

    public PayrollService(
            PayrollRepository payrollRepository,
            EmployeeRepository employeeRepository) {

        this.payrollRepository = payrollRepository;
        this.employeeRepository = employeeRepository;
    }

    // Generate Payroll
    public PayrollResponseDTO generatePayroll(PayrollRequestDTO dto) {

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + dto.getEmployeeId()));

        // Prevent duplicate payroll generation
        if (payrollRepository.existsByEmployeeIdAndMonthAndYear(
                dto.getEmployeeId(),
                dto.getMonth(),
                dto.getYear())) {

            throw new RuntimeException(
                    "Payroll already generated for this employee for "
                            + dto.getMonth() + " " + dto.getYear());
        }

        Payroll payroll = new Payroll();

        payroll.setEmployee(employee);
        payroll.setMonth(dto.getMonth());
        payroll.setYear(dto.getYear());

        payroll.setBasicSalary(employee.getSalary());

        payroll.setHra(dto.getHra() == null ? 0.0 : dto.getHra());
        payroll.setAllowance(dto.getAllowance() == null ? 0.0 : dto.getAllowance());
        payroll.setBonus(dto.getBonus() == null ? 0.0 : dto.getBonus());
        payroll.setTax(dto.getTax() == null ? 0.0 : dto.getTax());
        payroll.setPf(dto.getPf() == null ? 0.0 : dto.getPf());
        payroll.setOtherDeduction(
                dto.getOtherDeduction() == null ? 0.0 : dto.getOtherDeduction());

        // Gross Salary
        payroll.setGrossSalary(
                payroll.getBasicSalary()
                        + payroll.getHra()
                        + payroll.getAllowance()
                        + payroll.getBonus());

        // Net Salary
        payroll.setNetSalary(
                payroll.getGrossSalary()
                        - payroll.getTax()
                        - payroll.getPf()
                        - payroll.getOtherDeduction());

        payroll.setStatus(PayrollStatus.GENERATED);

        Payroll savedPayroll = payrollRepository.save(payroll);

        return PayrollMapper.toDTO(savedPayroll);
    }

    // Get All Payrolls
    public List<PayrollResponseDTO> getAllPayrolls() {

        return payrollRepository.findAll()
                .stream()
                .map(PayrollMapper::toDTO)
                .collect(Collectors.toList());
    }
// Get Payroll By Id
public PayrollResponseDTO getPayrollById(Long id) {

    Payroll payroll = payrollRepository.findById(id)
            .orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Payroll not found with id : " + id));

    return PayrollMapper.toDTO(payroll);
}
    // Get Payroll By Employee
    public List<PayrollResponseDTO> getPayrollByEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Employee not found with id: " + employeeId));

        return payrollRepository.findByEmployee(employee)
                .stream()
                .map(PayrollMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Pay Salary
    public PayrollResponseDTO paySalary(Long payrollId) {

        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Payroll not found"));

        payroll.setStatus(PayrollStatus.PAID);

        Payroll updated = payrollRepository.save(payroll);

        return PayrollMapper.toDTO(updated);
    }
}