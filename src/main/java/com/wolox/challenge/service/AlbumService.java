package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.AlbumRestClient;
import com.wolox.challenge.model.Album;
import com.wolox.challenge.rest.repository.IPrivilegeRepository;
import com.wolox.challenge.rest.repository.jpa.privilege.Privilege;
import com.wolox.challenge.rest.repository.jpa.privilege.PrivilegeId;

@Service
public class AlbumService {

	AlbumRestClient albumRestClient;
	IPrivilegeRepository privilegeRepository;

	@Autowired
	public AlbumService(AlbumRestClient albumRestClient, IPrivilegeRepository privilegeRepository) {
		this.albumRestClient = albumRestClient;
		this.privilegeRepository = privilegeRepository;
	}

	public List<Album> getAll() {
		return albumRestClient.findAllAlbums();
	}

	public List<Album> getAlbumsByUser(String userId) {
		return albumRestClient.findAlbumsByUserId(userId); 
	}
	
	public void saveAlbumByPrivilegeAndUser(String album_id, String user_id, String privileges) {
		Privilege privilege = getPrivilege(album_id, user_id, privileges);
		privilegeRepository.saveAndFlush(privilege);
	}
	
	private Privilege getPrivilege(String album_id, String user_id, String privileges) {
		Privilege privilege = new Privilege();
		privilege.setId(new PrivilegeId(user_id,album_id));
		privilege.setPrivileges(privileges);
		return privilege;
	}
	
	public Boolean validatePrivilegeRecord(String user_id,String album_id) {
		return privilegeRepository.existsById(new PrivilegeId(user_id,album_id));
	}
}
