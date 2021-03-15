package com.wolox.challenge.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wolox.challenge.model.Post;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRestClientTest {

	@Autowired
	PostRestClient postRestClient;
	
	@Test
	public void testFindPostById() {
		Post result = postRestClient.findPostById("11");
		assertEquals("et ea vero quia laudantium autem", result.getTitle());
	}
	
	@Test
	public void testFindAllPosts() {
		List<Post> result = postRestClient.findAllPosts();
		assertEquals(100, result.size());
	}
	
	@Test
	public void testFindPostsByUserId() {
		List<Post> result = postRestClient.findPostsByUserId("4");
		assertEquals(10, result.size());
	}

}
