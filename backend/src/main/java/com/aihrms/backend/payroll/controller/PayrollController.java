package com.aihrms.backend.payroll.controller;

import com.aihrms.backend.payroll.dto.PayrollRequestDTO;
import com.aihrms.backend.payroll.dto.PayrollResponseDTO;
import com.aihrms.backend.payroll.service.PayrollService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    // Generate Payroll
    @PostMapping
    public ResponseEntity<PayrollResponseDTO> generatePayroll(
            @Valid @RequestBody PayrollRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(payrollService.generatePayroll(dto));
    }

    // Get All Payrolls
    @GetMapping
    public ResponseEntity<List<PayrollResponseDTO>> getAllPayrolls() {

        return ResponseEntity.ok(
                payrollService.getAllPayrolls()
        );
    }

    // Get Payroll History by Employee
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PayrollResponseDTO>> getPayrollByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(
                payrollService.getPayrollByEmployee(employeeId)
        );
    }
// Get Payroll By Id
@GetMapping("/{id}")
public ResponseEntity<PayrollResponseDTO> getPayrollById(
        @PathVariable Long id) {

    return ResponseEntity.ok(
            payrollService.getPayrollById(id)
    );
}
    // Pay Salary
    @PutMapping("/{payrollId}/pay")
    public ResponseEntity<PayrollResponseDTO> paySalary(
            @PathVariable Long payrollId) {

        return ResponseEntity.ok(
                payrollService.paySalary(payrollId)
        );
    }

}