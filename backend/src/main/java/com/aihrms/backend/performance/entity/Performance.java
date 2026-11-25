package com.aihrms.backend.performance.entity;

import com.aihrms.backend.employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "performance")
@Data
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    @Column(length = 1000)
    private String goals;

    @Column(length = 1000)
    private String comments;

    private LocalDate reviewDate;

    private String reviewer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    @JsonIgnoreProperties({"department", "attendances", "leaves", "hibernateLazyInitializer", "handler"})
    private Employee employee;
public Employee getEmployee() {
    return employee;
}


public void setEmployee(Employee employee) {
    this.employee = employee;
}
}