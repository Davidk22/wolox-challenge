package com.wolox.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.UserRestClient;
import com.wolox.challenge.model.User;
import com.wolox.challenge.rest.repository.IPrivilegeRepository;

@Service
public class UserService {

	UserRestClient userRestClient;
	IPrivilegeRepository privilegeRepository;

	@Autowired
	public UserService(UserRestClient userRestClient, IPrivilegeRepository privilegeRepository) {
		this.userRestClient = userRestClient;
		this.privilegeRepository = privilegeRepository;
	}

	public List<User> getAllUsers() {
		return userRestClient.findAllUsers();
	}

	public User getUserById(String userId) {
		return userRestClient.findUserById(userId);
	}

	public List<User> getUsersByPrivilegeAndAlbum(String albumId,String privileges){
		List<String> users =privilegeRepository.findUsersByPrivilegeAndAlbum(privileges, albumId);
		return users.stream()
				.map(user -> userRestClient.findUserById(user))
				.collect(Collectors.toList());
	}

}
