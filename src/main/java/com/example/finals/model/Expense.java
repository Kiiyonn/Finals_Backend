package com.example.finals.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Getters
    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
