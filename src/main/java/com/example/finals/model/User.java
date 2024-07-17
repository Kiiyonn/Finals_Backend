package com.example.finals.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private double budget;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Expense> expenses;

    // Getters
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

    // Setters
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
