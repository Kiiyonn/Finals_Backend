package com.example.finals.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

// Entity class representing an expense in the database
@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Primary key, auto-incremented
    private Long id;

    @Column(nullable = false)  // Non-nullable field for expense amount
    private double amount;

    @Column(nullable = false)  // Non-nullable field for expense description
    private String description;

    @Column(nullable = false)  // Non-nullable field for the date of the expense
    @Temporal(TemporalType.DATE)  // Stores only the date part in the database
    private Date date;

    @ManyToOne  // Many-to-one relationship with the User entity
    @JoinColumn(name = "user_id", nullable = false)  // Foreign key column
    private User user;

    // Getters and setters for accessing and updating the fields
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

    @JsonIgnore  // Exclude user from serialization to avoid infinite recursion
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
