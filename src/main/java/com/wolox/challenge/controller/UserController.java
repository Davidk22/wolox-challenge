package com.wolox.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wolox-api/users")
public class UserController {

	@GetMapping()
	public String findAll() {
		return "Ther're users";
	}

}
