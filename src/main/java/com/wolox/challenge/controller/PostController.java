package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.model.Post;
import com.wolox.challenge.service.PostService;

@RestController
@RequestMapping("/wolox-api/posts")
public class PostController {

	PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<Post> getAll(){
		return postService.getAll();
	}
	
	@GetMapping("/{postId}")
	public Post getAllById(@PathVariable String postId){
		return postService.getAllPostsById(postId);
	}
}
