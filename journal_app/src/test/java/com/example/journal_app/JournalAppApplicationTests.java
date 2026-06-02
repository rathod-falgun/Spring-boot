package com.example.journal_app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.journal_app.entity.User;
import com.example.journal_app.repository.UserRepository;


// Beforeall runs before all test cases like whatever we have to initialize or do something 
// and BeforeEach runs before each test case not all 

// afterall and aftereach , they are same as above but all working is after test cases
@SpringBootTest
class JournalAppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserRepository userRepository;

	
	@ParameterizedTest
	@CsvSource({
		"xeon",
		"ram",
		"shyam",
		"zammpy","Vivek","tam","falgun"
	})
	public void findByname(String name){
		User u = userRepository.findByUsername(name);
		assertTrue(!u.getUsername().isEmpty());
    }

}
