package com.example.finals.controller;

import com.example.finals.model.User;
import com.example.finals.security.CustomUserDetailsService;
import com.example.finals.security.JwtUtil;
import com.example.finals.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    // Response class to encapsulate JWT in a structured JSON
    private static class JwtResponse {
        private final String jwt;

        public JwtResponse(String jwt) {
            this.jwt = jwt;
        }

        public String getJwt() {
            return jwt;
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
        logger.debug("Attempting to authenticate user: {}", user.getUsername());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials for user: {}", user.getUsername());
            return ResponseEntity.status(401).body("Incorrect username or password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        logger.debug("User authenticated successfully: {}", user.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt)); // Return JWT wrapped in JSON
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        logger.debug("Registering new user: {}", user.getUsername());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User newUser = userService.saveUser(user);
        logger.debug("User registered successfully: {}", user.getUsername());
        return ResponseEntity.ok(newUser);
    }
}
