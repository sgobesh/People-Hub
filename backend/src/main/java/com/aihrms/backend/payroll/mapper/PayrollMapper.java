package com.aihrms.backend.payroll.mapper;

import com.aihrms.backend.payroll.dto.PayrollResponseDTO;
import com.aihrms.backend.payroll.entity.Payroll;

public class PayrollMapper {

   public static PayrollResponseDTO toDTO(Payroll payroll) {

    PayrollResponseDTO dto = new PayrollResponseDTO();

    dto.setId(payroll.getId());

    dto.setEmployeeId(payroll.getEmployee().getId());

    dto.setEmployeeCode(payroll.getEmployee().getEmployeeId());

    dto.setEmployeeName(
            payroll.getEmployee().getFirstName() + " "
                    + payroll.getEmployee().getLastName());

    dto.setMonth(payroll.getMonth());

    dto.setYear(payroll.getYear());

    dto.setBasicSalary(payroll.getBasicSalary());

    dto.setHra(payroll.getHra() == null ? 0.0 : payroll.getHra());

    dto.setAllowance(payroll.getAllowance() == null ? 0.0 : payroll.getAllowance());

    dto.setBonus(payroll.getBonus() == null ? 0.0 : payroll.getBonus());

    dto.setTax(payroll.getTax() == null ? 0.0 : payroll.getTax());

    dto.setPf(payroll.getPf() == null ? 0.0 : payroll.getPf());

    dto.setDeduction(
            payroll.getOtherDeduction() == null
                    ? 0.0
                    : payroll.getOtherDeduction());

    dto.setGrossSalary(
            payroll.getGrossSalary() == null
                    ? 0.0
                    : payroll.getGrossSalary());

    dto.setNetSalary(
            payroll.getNetSalary() == null
                    ? 0.0
                    : payroll.getNetSalary());

    dto.setStatus(payroll.getStatus());

    return dto;
}
}