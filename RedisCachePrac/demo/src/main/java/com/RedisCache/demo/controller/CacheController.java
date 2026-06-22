package com.RedisCache.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RedisCache.demo.entity.UserProfile;
import com.RedisCache.demo.services.UserProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class CacheController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/getUser/{name}")
    public ResponseEntity<?> getUser(@PathVariable String name) {
        UserProfile user = userProfileService.getUser(name);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create-user")
    public HttpStatus addUser(@RequestBody UserProfile userProfile) {
        HttpStatus code = userProfileService.SetUser(userProfile);
        return code;
    }

}
