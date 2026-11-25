package com.aihrms.backend.recruitment.dto;

import com.aihrms.backend.recruitment.entity.JobStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JobRequestDTO {

    @NotBlank(message = "Job Title is required")
    private String jobTitle;

    @NotBlank(message = "Department is required")
    private String department;

    @NotBlank(message = "Location is required")
    private String location;

    private String description;

    @NotNull(message = "Vacancies are required")
    private Integer vacancies;

    @NotNull(message = "Salary is required")
    private Double salary;

    @NotNull(message = "Status is required")
    private JobStatus status;

    public JobRequestDTO() {
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVacancies() {
        return vacancies;
    }

    public void setVacancies(Integer vacancies) {
        this.vacancies = vacancies;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }
}