package com.example.finals.model;

import java.util.Date;

// Data Transfer Object for Expense to facilitate data handling and prevent recursion issues
public class ExpenseDTO {
    private Long id; // Unique identifier for the expense
    private double amount; // Monetary value of the expense
    private String description; // Description of the expense
    private Date date; // Date when the expense occurred
    private Long userId; // User ID associated with the expense to avoid recursion with user entity

    // Default constructor
    public ExpenseDTO() {}

    // Parameterized constructor for initializing an ExpenseDTO
    public ExpenseDTO(Long id, double amount, String description, Date date, Long userId) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.userId = userId;
    }

    // Getters and setters for each property
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
