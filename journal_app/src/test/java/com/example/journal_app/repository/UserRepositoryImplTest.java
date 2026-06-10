package com.example.journal_app.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.journal_app.entity.User;
import com.example.journal_app.repository.UserRepositoryImpl;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepositoryImpl;

    @Test
    public void testGetUserForSA_returnsNonEmptyList() {
        List<User> users = userRepositoryImpl.getUserForSA();
        System.out.println(users);
        assertFalse(users.isEmpty());
    }
}
