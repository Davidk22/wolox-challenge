package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.CommentRestClient;
import com.wolox.challenge.model.Comment;

@Service
public class CommentService {

	CommentRestClient commentRestClient;

	@Autowired
	public CommentService(CommentRestClient commentRestClient) {
		this.commentRestClient = commentRestClient;
	}

	public List<Comment> getAll() {
		return commentRestClient.findAllComments();
	}

	public List<Comment> getAllCommentsByName(String name){
		return commentRestClient.findCommentsByName(name);
	}
	
	public List<Comment> getAllCommentsByUserId(String userId){
		return commentRestClient.findCommentsByUserId(userId);
	}
	
}
