package com.aihrms.backend.security.entity;

import com.aihrms.backend.employee.entity.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    // ==========================================
    // Primary Key
    // ==========================================

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // User Details
    // ==========================================

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    // ==========================================
    // Employee Mapping
    // ==========================================

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(
            name = "employee_id",
            unique = true
    )
    private Employee employee;

    // ==========================================
    // Constructors
    // ==========================================

    public User() {
    }

    public User(
            Long id,
            String name,
            String email,
            String password,
            Role role,
            Employee employee) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.employee = employee;
    }

    // ==========================================
    // Getters & Setters
    // ==========================================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Password should always be stored encoded.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}