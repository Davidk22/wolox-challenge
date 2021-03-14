package com.wolox.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.CommentRestClient;
import com.wolox.challenge.model.Comment;
import com.wolox.challenge.model.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.debug("Consuming commentRestClient - Listing all comments");
		return commentRestClient.findAllComments();
	}

	public List<Comment> getAllCommentsByName(String name){
		log.debug("Consuming commentRestClient - Listing all comments by name. Name:"+name);
		return commentRestClient.findCommentsByName(name);
	}
	
	public List<Comment> getAllCommentsByUserId(String userId){
		log.debug("Consuming commentRestClient - Listing all comments by user. User:"+userId);
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
		log.debug("Consuming commentRestClient - Listing all comments by post. Post:");
		return commentRestClient.findCommentsByPost(postId);
	}
}
