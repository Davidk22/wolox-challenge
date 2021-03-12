package com.wolox.challenge.adapter;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.wolox.challenge.model.Photo;

@Repository
public class PhotoRestClient {
	private final static String FIND_BY_ID="/photos/{id}";
	private final static String FIND_ALL="/photos";
	private final static String FIND_ALL_PHOTOS_BY_ALBUMID="/album/{id}/photos";
	
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
	
	public List<Photo> findAllPhotos(){
		return this.webClient.get()
				.uri(FIND_ALL)
				.retrieve()
				.bodyToFlux(Photo.class)
				.collectList()
				.block();
	}
	
	public List<Photo> findPhotosByAlbumId(String albumId){
		return this.webClient.get()
				.uri(FIND_ALL_PHOTOS_BY_ALBUMID, albumId)
				.retrieve()
				.bodyToFlux(Photo.class)
				.collectList()
				.block();	
	}
}
