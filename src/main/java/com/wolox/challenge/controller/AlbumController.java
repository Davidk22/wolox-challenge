package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.model.Album;
import com.wolox.challenge.rest.repository.jpa.privilege.Privilege;
import com.wolox.challenge.service.AlbumService;

@RestController
@RequestMapping("/wolox-api/albums")
public class AlbumController {

	@Autowired
	AlbumService albumService;

	@GetMapping
	public List<Album> findAll() {
		return albumService.getAll();
	}

	@GetMapping("/{userId}")
	public List<Album> findAllAlbumsByUser(@PathVariable("userId") String userId) {
		return albumService.getAlbumsByUser(userId);
	}

	@PostMapping("/insert")
	public ResponseEntity<String> insertSharedAlbumWithUserAndPrivileges(@RequestBody Privilege privilege) {
		return albumService.saveAlbumByPrivilegeAndUser(privilege.getId().getAlbumId(),
				privilege.getId().getUserId(), privilege.getPrivileges());
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateSharedAlbumWithUserAndPrivileges(@RequestBody Privilege privilege) {
		return albumService.updateAlbumByPrivilegeAndUser(privilege.getId().getAlbumId(),
				privilege.getId().getUserId(), privilege.getPrivileges());
	}

}
