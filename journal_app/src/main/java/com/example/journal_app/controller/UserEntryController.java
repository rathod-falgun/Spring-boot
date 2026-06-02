package com.example.journal_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.journal_app.entity.User;
import com.example.journal_app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    private PasswordEncoder passwordencoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    // @GetMapping("/all")
    // public List<User> getAllUser() {
    // return userService.getAll();
    // }

    @PutMapping
    public ResponseEntity<?> UpdateUser(@RequestBody User user) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String password = user.getPassword();
        System.out.println("\n" + username + " " + password + "\n");
        User u = userService.findByname(username);
        if (u != null) {
            u.setUsername(user.getUsername());
            u.setPassword(passwordencoder.encode(password));
            userService.saveEntry(u);
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{username}")
    public void  deleteByUser(@PathVariable String Username){
    }

}
