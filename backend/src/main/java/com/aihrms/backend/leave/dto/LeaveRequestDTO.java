package com.aihrms.backend.leave.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LeaveRequestDTO {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotBlank(message = "Leave Type is required")
    private String leaveType;

    @NotNull(message = "Start Date is required")
    @FutureOrPresent(message = "Start Date cannot be in the past")
    private LocalDate startDate;

    @NotNull(message = "End Date is required")
    @FutureOrPresent(message = "End Date cannot be in the past")
    private LocalDate endDate;

    private String reason;

    private String remarks;

    public LeaveRequestDTO() {
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}