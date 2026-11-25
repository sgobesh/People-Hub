package com.aihrms.backend.attendance.entity;

import com.aihrms.backend.employee.entity.Employee;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "attendance")
public class Attendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private LocalDate attendanceDate;


    private LocalDateTime checkIn;


    private LocalDateTime checkOut;


    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;



    public Attendance() {
    }



    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public LocalDate getAttendanceDate() {
        return attendanceDate;
    }


    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }


    public LocalDateTime getCheckIn() {
        return checkIn;
    }


    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }


    public LocalDateTime getCheckOut() {
        return checkOut;
    }


    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }


    public AttendanceStatus getStatus() {
        return status;
    }


    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }


    public Employee getEmployee() {
        return employee;
    }


    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}