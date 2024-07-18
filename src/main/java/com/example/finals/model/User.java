package com.example.finals.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Entity class representing a user in the database
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Primary key, auto-incremented
    private Long id;

    @Column(nullable = false, unique = true)  // Unique username for each user
    private String username;

    @Column(nullable = false)  // Encrypted password for user authentication
    private String password;

    @Column(nullable = false)  // Financial budget of the user
    private double budget;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)  // One-to-many relationship with expenses
    private List<Expense> expenses;

    // Getters for accessing property values
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBudget() {
        return budget;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    // Setters for updating property values
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
