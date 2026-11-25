package com.aihrms.backend.department.dto;

public class DepartmentDTO {

    private Long id;
    private String departmentCode;
    private String departmentName;
    private String description;

    public DepartmentDTO() {
    }

    public DepartmentDTO(Long id, String departmentCode,
                         String departmentName,
                         String description) {
        this.id = id;
        this.departmentCode = departmentCode;
        this.departmentName = departmentName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}