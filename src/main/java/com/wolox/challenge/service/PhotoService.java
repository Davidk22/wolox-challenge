package com.wolox.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.PhotoRestClient;
import com.wolox.challenge.model.Album;
import com.wolox.challenge.model.Photo;
import com.wolox.challenge.rest.repository.IPrivilegeRepository;

@Service
public class PhotoService {

	PhotoRestClient photoRestClient;
	IPrivilegeRepository privilegeRepository;
	AlbumService albumService;
	
	@Autowired
	public PhotoService(PhotoRestClient photoRestClient, IPrivilegeRepository privilegeRepository,
			AlbumService albumService) {
		this.photoRestClient = photoRestClient;
		this.privilegeRepository = privilegeRepository;
		this.albumService = albumService;
	}
	
	public List<Photo> getAll() {
		return photoRestClient.findAllPhotos();
	}
	
	public List<Photo> getPhotosByUser(String userId){
		List<Album> albumsByUser = albumService.getAlbumsByUser(userId);
		return albumsByUser.stream()
			.map(album -> getPhotosByAlbum(album.getId()))
			.collect(Collectors.toList())
			.stream()
			.flatMap(photos -> photos
					.stream())
			.collect(Collectors.toList());
	}
	
	public List<Photo> getPhotosByAlbum(String albumId){
		return photoRestClient.findPhotosByAlbumId(albumId);
	}
	
	
}
