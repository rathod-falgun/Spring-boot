package com.example.journal_app.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journal_app.entity.User;
import com.example.journal_app.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/public")
public class PublicController {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(PublicController.class);

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) {
        return userService.findByname(username);
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(user.getRoles());
            userService.saveEntry(user);
            logger.info("User Created Successfully");
            return user;
        } catch (Exception e) {
            logger.debug("YOU HAVE NOT MATCH THE REQUIREMENTS");
            return user;
        }

    }

    @GetMapping
    public String GreetingMessage() {
        return "<h2>Welcome to My Spring Boot Learning</h2/>";
    }
}
