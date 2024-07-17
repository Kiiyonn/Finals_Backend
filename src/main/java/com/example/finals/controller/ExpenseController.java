package com.example.finals.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finals.model.Expense;
import com.example.finals.model.ExpenseDTO;
import com.example.finals.model.User;
import com.example.finals.service.ExpenseService;
import com.example.finals.service.UserService;

// Controller class for handling expense-related API requests
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    // Get all expenses for the logged-in user
    @GetMapping
    public List<ExpenseDTO> getAllExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        List<Expense> expenses = expenseService.findAllExpensesForUser(userDetails.getUsername());
        return expenses.stream()
                .map(expense -> new ExpenseDTO(expense.getId(), expense.getAmount(), expense.getDescription(), expense.getDate(), expense.getUser().getId()))
                .collect(Collectors.toList());
    }

    // Create a new expense for the logged-in user
    @PostMapping
    public ResponseEntity<?> createExpense(@RequestBody ExpenseDTO expenseDTO, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByUsername(userDetails.getUsername());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Expense expense = new Expense();
        expense.setAmount(expenseDTO.getAmount());
        expense.setDescription(expenseDTO.getDescription());
        expense.setDate(expenseDTO.getDate());
        expense.setUser(user);
        Expense savedExpense = expenseService.saveExpense(expense);

        return ResponseEntity.ok(new ExpenseDTO(savedExpense.getId(), savedExpense.getAmount(), savedExpense.getDescription(), savedExpense.getDate(), savedExpense.getUser().getId()));
    }

    // Update an existing expense for the logged-in user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByUsername(userDetails.getUsername());
        Expense existingExpense = expenseService.findExpenseById(id);
        if (existingExpense == null || user == null || !existingExpense.getUser().getId().equals(user.getId())) {
            return ResponseEntity.notFound().build();
        }

        existingExpense.setDescription(expenseDTO.getDescription());
        existingExpense.setAmount(expenseDTO.getAmount());
        existingExpense.setDate(expenseDTO.getDate());
        expenseService.updateExpense(existingExpense);

        return ResponseEntity.ok(new ExpenseDTO(existingExpense.getId(), existingExpense.getAmount(), existingExpense.getDescription(), existingExpense.getDate(), existingExpense.getUser().getId()));
    }

    // Delete an existing expense for the logged-in user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findUserByUsername(userDetails.getUsername());
        Expense expense = expenseService.findExpenseById(id);
        if (expense == null || user == null || !expense.getUser().getId().equals(user.getId())) {
            return ResponseEntity.notFound().build();
        }

        expenseService.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}
