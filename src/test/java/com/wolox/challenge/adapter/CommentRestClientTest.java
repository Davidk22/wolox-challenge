package com.wolox.challenge.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wolox.challenge.model.Comment;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRestClientTest {
	@Autowired
	CommentRestClient commentRestClient;
	
	@Test
	public void testFindCommentById() {
		Comment result = commentRestClient.findCommentById("11");
		assertEquals("Veronica_Goodwin@timmothy.net", result.getEmail());
	}
	
	@Test
	public void testFindAllComments() {
		List<Comment> result = commentRestClient.findAllComments();
		assertEquals(500, result.size());
	}
	
	@Test
	public void testFindCommentsByPost() {
		List<Comment> result = commentRestClient.findCommentsByPost("1");
		assertEquals("Jayne_Kuhic@sydney.com", result.get(1).getEmail());
	}
	
	@Test
	public void testFindCommentsByName() {
		List<Comment> result = commentRestClient.findCommentsByName("alias odio sit");
		assertEquals("1", result.get(0).getPostId());
	}
}
