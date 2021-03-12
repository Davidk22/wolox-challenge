package com.wolox.challenge.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.Album;

@Repository
public class AlbumRestClient {

	private final static String FIND_BY_ID="/albums/{id}";
	private final static String FIND_ALL="/albums";
	private final static String FIND_ALL_ALBUMS_BY_USERID ="/users/{id}/albums";
	
	private final WebClient webClient;

	public AlbumRestClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Album findAlbumsById(String id) {
		return this.webClient.get()
				.uri(FIND_BY_ID, id)
				.retrieve()
				.bodyToMono(Album.class)
				.block();
	}
	
	public List<Album> findAllAlbums(){
		return this.webClient.get()
				.uri(FIND_ALL)
				.retrieve()
				.bodyToFlux(Album.class)
				.collectList()
				.block();
	}
	
	public List<Album> findAlbumsByUserId(String userId){
		return this.webClient.get()
				.uri(FIND_ALL_ALBUMS_BY_USERID, userId)
				.retrieve()
				.bodyToFlux(Album.class)
				.collectList()
				.block();	
	}
	
}
