package com.wolox.challenge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.UserRestClient;
import com.wolox.challenge.model.User;
import com.wolox.challenge.rest.repository.IPrivilegeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		log.debug("Consuming UserRestClient - Listing all users");
		return userRestClient.findAllUsers();
	}

	public User getUserById(String userId) {
		log.debug("Consuming UserRestClient - Finding user by id User: "+userId);
		return userRestClient.findUserById(userId);
	}

	public List<User> getUsersByPrivilegeAndAlbum(String albumId,String privileges){
		log.debug("Consuming UserRestClient - Listing all users by albumId and Privilege. Album:"+albumId+", Privilege:"+privileges);
		List<String> users =privilegeRepository.findUsersByPrivilegeAndAlbum(privileges, albumId);
		return users.stream()
				.map(user -> userRestClient.findUserById(user))
				.collect(Collectors.toList());
	}
	
}
