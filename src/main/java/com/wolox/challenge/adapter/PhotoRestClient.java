package com.wolox.challenge.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.Photo;

@Repository
public class PhotoRestClient {
private final static String FIND_BY_ID="/photos/{id}";
	
	private final WebClient webClient;

	public PhotoRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Photo findPhotoById(String id) {
		return this.webClient.get()
				.uri(FIND_BY_ID, id)
				.retrieve()
				.bodyToMono(Photo.class)
				.block();
	}
}
