package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		if (albumService.validatePrivilegeData(privilege.getId().getAlbumId(), privilege.getId().getUserId(),
				privilege.getPrivileges())) {
			if (!albumService.validatePrivilegeRecord(privilege.getId().getUserId(), privilege.getId().getAlbumId())) {
				if (albumService.validatePrivilege(privilege.getPrivileges())) {
					albumService.saveAlbumByPrivilegeAndUser(privilege.getId().getAlbumId(),
							privilege.getId().getUserId(), privilege.getPrivileges());
					return new ResponseEntity<String>("Privilige created succesfully", HttpStatus.CREATED);
				}
				return new ResponseEntity<String>("Privilege value must be 0 (read), 1(write) or 2(both)", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("The privilege record already exists", HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("Data can't be null or empty", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateSharedAlbumWithUserAndPrivileges(@RequestBody Privilege privilege) {
		if (albumService.validatePrivilegeData(privilege.getId().getAlbumId(), privilege.getId().getUserId(),
				privilege.getPrivileges())) {
			if (albumService.validatePrivilegeRecord(privilege.getId().getUserId(), privilege.getId().getAlbumId())) {
				if (albumService.validatePrivilege(privilege.getPrivileges())) {
					albumService.saveAlbumByPrivilegeAndUser(privilege.getId().getAlbumId(),
							privilege.getId().getUserId(), privilege.getPrivileges());
					return new ResponseEntity<String>("Privilige updated succesfully", HttpStatus.ACCEPTED);
				}
				return new ResponseEntity<String>("Privilege value must be 0 (read), 1(write) or 2(both)",
						HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<String>("The privilege record doesn't exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Data can't be null or empty", HttpStatus.BAD_REQUEST);
	}

}
