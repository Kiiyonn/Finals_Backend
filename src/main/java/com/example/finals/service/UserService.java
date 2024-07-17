package com.example.finals.service;

import com.example.finals.model.User;
import com.example.finals.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User findUserByUsername(String username) {
        // This method returns a User or null if not found
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long id) {
        // This method also returns User directly, handling null via orElse(null)
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
