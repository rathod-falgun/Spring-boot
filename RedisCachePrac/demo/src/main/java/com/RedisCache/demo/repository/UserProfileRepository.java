package com.RedisCache.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.RedisCache.demo.entity.UserProfile;
import java.util.List;


public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    public UserProfile findByName(String name);
}
