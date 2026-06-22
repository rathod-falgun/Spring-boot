package com.RedisCache.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userProfile\r\n" + //
        "\r\n" + //
        "")
@Data
@NoArgsConstructor
public class UserProfile {
    @Id
    private String id;
    private String name;
    private int age;
}
