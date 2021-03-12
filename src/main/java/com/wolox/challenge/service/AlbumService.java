package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wolox.challenge.adapter.AlbumRestClient;
import com.wolox.challenge.model.Album;

public class AlbumService {

	AlbumRestClient albumRestClient;

	@Autowired
	public AlbumService(AlbumRestClient albumRestClient) {
		this.albumRestClient = albumRestClient;
	}

	public List<Album> getAll() {
		return albumRestClient.findAllAlbums();
	}

	public List<Album> getAlbumsByUser(String userId) {
		return albumRestClient.findAlbumsByUserId(userId); 
	}
}
