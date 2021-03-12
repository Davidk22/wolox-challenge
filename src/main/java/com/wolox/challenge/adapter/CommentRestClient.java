package com.wolox.challenge.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.Comment;

@Repository
public class CommentRestClient {
	private final static String FIND_BY_ID="/comments/{id}";
	private final static String FIND_ALL="/comments";
	private final static String FIND_ALL_COMMENTS_BY_USERID="/users/{id}/comments";
	private final static String FIND_ALL_COMMENTS_BY_NAME="/comments?name={name}";
	
	private final WebClient webClient;

	public CommentRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Comment findCommentById(String id) {
		return this.webClient.get()
				.uri(FIND_BY_ID, id)
				.retrieve()
				.bodyToMono(Comment.class)
				.block();
	}
	
	public List<Comment> findAllComments(){
		return this.webClient.get()
				.uri(FIND_ALL)
				.retrieve()
				.bodyToFlux(Comment.class)
				.collectList()
				.block();
	}
	
	public List<Comment> findCommentsByUserId(String userId){
		return this.webClient.get()
				.uri(FIND_ALL_COMMENTS_BY_USERID, userId)
				.retrieve()
				.bodyToFlux(Comment.class)
				.collectList()
				.block();
	}
	
	public List<Comment> findCommentsByName(String name){
		return this.webClient.get()
				.uri(FIND_ALL_COMMENTS_BY_NAME, name)
				.retrieve()
				.bodyToFlux(Comment.class)
				.collectList()
				.block();
	}
	
}
