package com.wolox.challenge.adapter;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wolox.challenge.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRestClientTest {

	@Autowired
	UserRestClient userRestClient;
	
	@Test
	public void testGetAllUsers() {
        List<User> result = userRestClient.findAllUsers();
        assertEquals(10, result.size());
	}
	
	@Test
	public void testFindUserById() {
		User result = userRestClient.findUserById("3");
		assertEquals("Samantha", result.getUsername());
	}
	
}
