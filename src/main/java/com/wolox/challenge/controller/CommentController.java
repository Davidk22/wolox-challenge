package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.model.Comment;
import com.wolox.challenge.service.CommentService;

@RestController
@RequestMapping("wolox-api/comments")
public class CommentController {

	@Autowired
	CommentService commentService;
	
	@GetMapping()
	public List<Comment> getAll(){
		return commentService.getAll();
	}
	
	@GetMapping("/name/{name}")
	public List<Comment> getAllCommentsByName(@PathVariable String name){
		return commentService.getAllCommentsByName(name);
	}
	
	@GetMapping("/{userId}")
	public List<Comment> gettAllCommentsByUser(@PathVariable String userId){
		return commentService.getAllCommentsByUserId(userId);
	}
	
}
