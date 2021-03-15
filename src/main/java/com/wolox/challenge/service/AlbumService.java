package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.AlbumRestClient;
import com.wolox.challenge.exception.NullDataException;
import com.wolox.challenge.exception.PrivilegeAlreadyExistsException;
import com.wolox.challenge.exception.PrivilegeNotFoundException;
import com.wolox.challenge.exception.PrivilegeValueNotAllowedException;
import com.wolox.challenge.model.Album;
import com.wolox.challenge.rest.repository.IPrivilegeRepository;
import com.wolox.challenge.rest.repository.jpa.privilege.Privilege;
import com.wolox.challenge.rest.repository.jpa.privilege.PrivilegeId;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlbumService {

	private final static String PRIVILEGE_CREATED ="Privilige created succesfully";
	private final static String PRIVILEGE_UPDATED ="Privilige updated succesfully";
	
	AlbumRestClient albumRestClient;
	IPrivilegeRepository privilegeRepository;

	@Autowired
	public AlbumService(AlbumRestClient albumRestClient, IPrivilegeRepository privilegeRepository) {
		this.albumRestClient = albumRestClient;
		this.privilegeRepository = privilegeRepository;
	}

	public List<Album> getAll() {
		log.debug("Consuming AlbumRestClient - Listing all albums");
		return albumRestClient.findAllAlbums();
	}

	public List<Album> getAlbumsByUser(String userId) {
		log.debug("Consuming AlbumRestClient - Listing all albums by user: "+userId);
		return albumRestClient.findAlbumsByUserId(userId); 
	}
	
	public ResponseEntity<String> saveAlbumByPrivilegeAndUser(String album_id, String user_id, String privileges) {
		Privilege privilege = getPrivilege(album_id, user_id, privileges);
		log.debug("Consuming AlbumRestClient - Saving privilege AlbumId: "+album_id+", UserId: "+user_id+", Privilege: "+privileges);
		if (validatePrivilegeData(privilege.getId().getAlbumId(), privilege.getId().getUserId(),
				privilege.getPrivileges())) {
			if (!validatePrivilegeRecord(privilege.getId().getUserId(), privilege.getId().getAlbumId())) {
				if (validatePrivilege(privilege.getPrivileges())) {
					privilegeRepository.saveAndFlush(privilege);
					return new ResponseEntity<String>(PRIVILEGE_CREATED, HttpStatus.CREATED);
				}
				throw new PrivilegeValueNotAllowedException();
			}
			throw new PrivilegeAlreadyExistsException(album_id, user_id);
		}
		throw new NullDataException();
	}
	
	public ResponseEntity<String> updateAlbumByPrivilegeAndUser(String album_id, String user_id, String privileges){
		Privilege privilege = getPrivilege(album_id, user_id, privileges);
		log.debug("Consuming AlbumRestClient - Updating privilege AlbumId: "+album_id+", UserId: "+user_id+", Privilege: "+privileges);
		if (validatePrivilegeData(privilege.getId().getAlbumId(), privilege.getId().getUserId(),
				privilege.getPrivileges())) {
			if (validatePrivilegeRecord(privilege.getId().getUserId(), privilege.getId().getAlbumId())) {
				if (validatePrivilege(privilege.getPrivileges())) {
					privilegeRepository.saveAndFlush(privilege);
					return new ResponseEntity<String>(PRIVILEGE_UPDATED, HttpStatus.ACCEPTED);
				}
				throw new PrivilegeValueNotAllowedException();
			}
			throw new PrivilegeNotFoundException(album_id, user_id);
		}
		throw new NullDataException();
	}
	
	private Privilege getPrivilege(String album_id, String user_id, String privileges) {
		Privilege privilege = new Privilege();
		privilege.setId(new PrivilegeId(user_id,album_id));
		privilege.setPrivileges(privileges);
		return privilege;
	}
	
	public Boolean validatePrivilegeRecord(String user_id,String album_id) {
		log.debug("Consuming AlbumRestClient - Validating privilege AlbumId: "+album_id+", UserId: "+user_id);
		return privilegeRepository.existsById(new PrivilegeId(user_id,album_id));
	}
	
	public Boolean validatePrivilegeData(String album_id, String user_id, String privileges) {
		return (album_id != null ? !album_id.isEmpty() : false) &&
			   (user_id != null ? !user_id.isEmpty() : false) &&
			   (privileges != null ? !privileges.isEmpty() : false);
	}
	
	public Boolean validatePrivilege(String privileges) {
		return (privileges.equals("0") || privileges.equals("1") || privileges.equals("2"));
	}
}
