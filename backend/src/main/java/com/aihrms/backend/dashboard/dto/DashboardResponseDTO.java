package com.aihrms.backend.dashboard.dto;

public class DashboardResponseDTO {

    private Long totalEmployees;
    private Long totalDepartments;

    private Long totalAttendanceToday;
    private Long presentToday;
    private Long absentToday;

    private Long employeesOnLeave;
    private Long pendingLeaves;
    private Long approvedLeaves;

    private Long totalPayrolls;
    private Long totalPerformanceReviews;

    private Long totalJobs;
    private Long openJobs;

    private Long totalCandidates;
    private Long selectedCandidates;
    private Long rejectedCandidates;

    public DashboardResponseDTO() {
    }

    public Long getTotalEmployees() {
        return totalEmployees;
    }

    public void setTotalEmployees(Long totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public Long getTotalDepartments() {
        return totalDepartments;
    }

    public void setTotalDepartments(Long totalDepartments) {
        this.totalDepartments = totalDepartments;
    }

    public Long getTotalAttendanceToday() {
        return totalAttendanceToday;
    }

    public void setTotalAttendanceToday(Long totalAttendanceToday) {
        this.totalAttendanceToday = totalAttendanceToday;
    }

    public Long getPresentToday() {
        return presentToday;
    }

    public void setPresentToday(Long presentToday) {
        this.presentToday = presentToday;
    }

    public Long getAbsentToday() {
        return absentToday;
    }

    public void setAbsentToday(Long absentToday) {
        this.absentToday = absentToday;
    }

    public Long getEmployeesOnLeave() {
        return employeesOnLeave;
    }

    public void setEmployeesOnLeave(Long employeesOnLeave) {
        this.employeesOnLeave = employeesOnLeave;
    }

    public Long getPendingLeaves() {
        return pendingLeaves;
    }

    public void setPendingLeaves(Long pendingLeaves) {
        this.pendingLeaves = pendingLeaves;
    }

    public Long getApprovedLeaves() {
        return approvedLeaves;
    }

    public void setApprovedLeaves(Long approvedLeaves) {
        this.approvedLeaves = approvedLeaves;
    }

    public Long getTotalPayrolls() {
        return totalPayrolls;
    }

    public void setTotalPayrolls(Long totalPayrolls) {
        this.totalPayrolls = totalPayrolls;
    }

    public Long getTotalPerformanceReviews() {
        return totalPerformanceReviews;
    }

    public void setTotalPerformanceReviews(Long totalPerformanceReviews) {
        this.totalPerformanceReviews = totalPerformanceReviews;
    }

    public Long getTotalJobs() {
        return totalJobs;
    }

    public void setTotalJobs(Long totalJobs) {
        this.totalJobs = totalJobs;
    }

    public Long getOpenJobs() {
        return openJobs;
    }

    public void setOpenJobs(Long openJobs) {
        this.openJobs = openJobs;
    }

    public Long getTotalCandidates() {
        return totalCandidates;
    }

    public void setTotalCandidates(Long totalCandidates) {
        this.totalCandidates = totalCandidates;
    }

    public Long getSelectedCandidates() {
        return selectedCandidates;
    }

    public void setSelectedCandidates(Long selectedCandidates) {
        this.selectedCandidates = selectedCandidates;
    }

    public Long getRejectedCandidates() {
        return rejectedCandidates;
    }

    public void setRejectedCandidates(Long rejectedCandidates) {
        this.rejectedCandidates = rejectedCandidates;
    }
}