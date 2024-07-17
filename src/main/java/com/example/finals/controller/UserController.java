package com.example.finals.controller;

import com.example.finals.model.User;
import com.example.finals.security.CurrentUserUtil;
import com.example.finals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me/budget")
    public ResponseEntity<User> updateUserBudget(@RequestBody User updatedUser, Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User existingUser = userService.findUserByUsername(username);

        if (existingUser == null) {
            return ResponseEntity.notFound().build();
        }

        existingUser.setBudget(updatedUser.getBudget());
        return ResponseEntity.ok(userService.saveUser(existingUser));
    }

    @DeleteMapping("/me")
    public ResponseEntity<?> deleteUser(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        User user = userService.findUserByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(user.getId());
        return ResponseEntity.ok().build();
    }
}
