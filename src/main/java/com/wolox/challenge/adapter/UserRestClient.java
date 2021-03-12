package com.wolox.challenge.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.User;

@Repository
public class UserRestClient {

	private final static String FIND_BY_ID="/users/{userId}";
	private final static String FIND_ALL="/users";
	
	private final WebClient webClient;

	public UserRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public User findUserById(String userId) {
		return this.webClient.get()
				.uri(FIND_BY_ID, userId)
				.retrieve()
				.bodyToMono(User.class)
				.block();
	}
	
	public List<User> findAllUsers(){
		return this.webClient.get()
				.uri(FIND_ALL)
				.retrieve()
				.bodyToFlux(User.class)
				.collectList()
				.block();
	}
	
}
