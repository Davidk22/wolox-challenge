package com.wolox.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.CommentRestClient;
import com.wolox.challenge.model.Comment;
import com.wolox.challenge.model.Post;

@Service
public class CommentService {

	CommentRestClient commentRestClient;
	PostService postService;

	@Autowired
	public CommentService(CommentRestClient commentRestClient, PostService postService) {
		this.commentRestClient = commentRestClient;
		this.postService = postService;
	}

	public List<Comment> getAll() {
		return commentRestClient.findAllComments();
	}

	public List<Comment> getAllCommentsByName(String name){
		return commentRestClient.findCommentsByName(name);
	}
	
	public List<Comment> getAllCommentsByUserId(String userId){
		List<Post> postsByUser = postService.getAllPostsByUserId(userId);
		return postsByUser.stream()
				.map(post -> getAllCommentsByPost(post.getId()))
				.collect(Collectors.toList())
				.stream()
				.flatMap(comment -> comment
						.stream())
				.collect(Collectors.toList());
	}
	
	
	public List<Comment> getAllCommentsByPost(String postId){
		return commentRestClient.findCommentsByPost(postId);
	}
}
