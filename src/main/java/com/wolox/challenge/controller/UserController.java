package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.model.User;
import com.wolox.challenge.service.UserService;

@RestController
@RequestMapping("/wolox-api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping()
	public List<User> findAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{albumId}/{privileges}")
	public List<User> findAllUsersByAlbumAndPrivileges(@PathVariable String albumId, @PathVariable String privileges) {
		return userService.getUsersByPrivilegeAndAlbum(albumId, privileges);
	}
}
