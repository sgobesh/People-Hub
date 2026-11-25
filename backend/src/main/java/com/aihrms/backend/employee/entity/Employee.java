package com.aihrms.backend.employee.entity;

import java.time.LocalDate;
import java.util.List;

import com.aihrms.backend.attendance.entity.Attendance;
import com.aihrms.backend.department.entity.Department;
import com.aihrms.backend.leave.entity.Leave;
import com.aihrms.backend.security.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String employeeId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String designation;

    private Double salary;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "gender")
    private String gender;

    // ===============================
    // Attendance
    // ===============================

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances;

    // ===============================
    // Leave
    // ===============================

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Leave> leaves;

    // ===============================
    // User (One-to-One)
    // ===============================

    @JsonIgnore
    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private User user;

    // ===============================
    // Department
    // ===============================

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Department department;

    public Employee() {
    }

    public Employee(
            Long id,
            String employeeId,
            String firstName,
            String lastName,
            String email,
            String phone,
            String designation,
            Double salary,
            LocalDate joiningDate,
            String gender,
            Department department) {

        this.id = id;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.designation = designation;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.gender = gender;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Leave> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<Leave> leaves) {
        this.leaves = leaves;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}