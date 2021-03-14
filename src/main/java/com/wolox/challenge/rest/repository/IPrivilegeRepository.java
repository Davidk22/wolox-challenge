package com.wolox.challenge.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wolox.challenge.rest.repository.jpa.privilege.Privilege;
import com.wolox.challenge.rest.repository.jpa.privilege.PrivilegeId;

public interface IPrivilegeRepository extends JpaRepository<Privilege, PrivilegeId> {

	@Query(value = "SELECT USER_ID FROM PRIVILEGES WHERE PRIVILEGES=?1 AND ALBUM_ID=?2", nativeQuery = true)
	List<String> findUsersByPrivilegeAndAlbum(String privilege, String albumId);

}
