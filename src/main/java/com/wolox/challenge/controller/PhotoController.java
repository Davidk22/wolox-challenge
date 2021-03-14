package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.model.Photo;
import com.wolox.challenge.service.PhotoService;

@RestController
@RequestMapping("/wolox-api/photos")
public class PhotoController {

	@Autowired
	PhotoService photoService;

	@GetMapping()
	public List<Photo> gettAllPhotos() {
		return photoService.getAll();
	}
	
	@GetMapping("/{userId}")
	public List<Photo> getPhotosByUser(@PathVariable String userId){
		return photoService.getPhotosByUser(userId);
	}
	
}
