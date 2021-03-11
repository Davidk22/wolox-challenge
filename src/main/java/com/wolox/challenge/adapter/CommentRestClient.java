package com.wolox.challenge.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.Comment;

@Repository
public class CommentRestClient {
private final static String FIND_BY_ID="/comments/{id}";
	
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
}
