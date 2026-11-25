package com.aihrms.backend.performance.dto;

import java.time.LocalDate;

public class PerformanceRequestDTO {

    private Long employeeId;

    private Integer rating;

    private String goals;

    private String comments;

    private LocalDate reviewDate;

    private String reviewer;


    public PerformanceRequestDTO() {
    }


    public Long getEmployeeId() {
        return employeeId;
    }


    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }


    public Integer getRating() {
        return rating;
    }


    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public String getGoals() {
        return goals;
    }


    public void setGoals(String goals) {
        this.goals = goals;
    }


    public String getComments() {
        return comments;
    }


    public void setComments(String comments) {
        this.comments = comments;
    }


    public LocalDate getReviewDate() {
        return reviewDate;
    }


    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }


    public String getReviewer() {
        return reviewer;
    }


    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}