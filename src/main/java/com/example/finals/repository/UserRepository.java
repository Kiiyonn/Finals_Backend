package com.example.finals.repository;

import com.example.finals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // This returns a User directly; adjust if Optional<User> is preferred.
}
