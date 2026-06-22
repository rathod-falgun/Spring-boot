package com.RedisCache.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.RedisCache.demo.entity.UserProfile;
import com.RedisCache.demo.repository.UserProfileRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private RedisService redisService;

    public UserProfile getUser(String username) {

        if (username == null || username.isBlank())
            return null;

        String key = username + "_info";

        UserProfile cachedUser = redisService.get(username, UserProfile.class);

        if (cachedUser != null) {
            return cachedUser;
        }

        UserProfile byName = userProfileRepository.findByName(username);
        if (byName != null) {
            redisService.set(key, byName, 330l);
            return byName;
        }
        return null;

    }

    public HttpStatus SetUser(UserProfile user) {
        if (user != null) {
            userProfileRepository.save(user);
            return HttpStatus.OK;
        } else {
            return HttpStatus.NOT_FOUND;
        }

    }
}
