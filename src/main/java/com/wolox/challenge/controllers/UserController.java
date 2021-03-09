package com.wolox.challenge.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.models.User;
import com.wolox.challenge.repositories.UserRepository;

@RestController
@RequestMapping("/wolox-api/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping()
	public String findAll() {
		List<User> users = userRepository.findAll();
		return users.isEmpty() ? "No users" : "Ther're users";
	}

}
