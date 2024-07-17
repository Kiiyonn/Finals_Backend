package com.example.finals.model;

import java.util.Date;

public class ExpenseDTO {
    private Long id;
    private double amount;
    private String description;
    private Date date;
    private Long userId; // only user ID to avoid recursion

    // Constructors
    public ExpenseDTO() {}

    public ExpenseDTO(Long id, double amount, String description, Date date, Long userId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
