package com.wolox.challenge.adapter;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.Album;

@Repository
public class AlbumRestClient {

private final static String FIND_BY_ID="/albums/{id}";
	
	private final WebClient webClient;

	public AlbumRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Album findAlbumById(String id) {
		return this.webClient.get()
				.uri(FIND_BY_ID, id)
				.retrieve()
				.bodyToMono(Album.class)
				.block();
	}
	
}
