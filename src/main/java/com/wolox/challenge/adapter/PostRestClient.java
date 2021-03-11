package com.wolox.challenge.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.Post;

@Repository
public class PostRestClient {

	
private final static String FIND_BY_ID="/posts/{id}";
	
	private final WebClient webClient;

	public PostRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Post findPostById(String id) {
		return this.webClient.get()
				.uri(FIND_BY_ID, id)
				.retrieve()
				.bodyToMono(Post.class)
				.block();
	}
}
