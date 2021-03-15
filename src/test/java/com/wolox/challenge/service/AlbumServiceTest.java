package com.wolox.challenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.wolox.challenge.exception.NullDataException;
import com.wolox.challenge.exception.PrivilegeAlreadyExistsException;
import com.wolox.challenge.exception.PrivilegeNotFoundException;
import com.wolox.challenge.exception.PrivilegeValueNotAllowedException;
import com.wolox.challenge.model.Album;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class AlbumServiceTest {

	@Autowired
	AlbumService service;

	@Test
	@Order(1)
	public void testGetAll() {
		List<Album> result = service.getAll();
		assertEquals(100, result.size());
	}

	@Test
	@Order(2)
	public void testGetAlbumsByUser() {
		List<Album> result = service.getAlbumsByUser("7");
		assertEquals("velit est quam", result.get(2).getTitle());
	}

	@Test
	@Order(3)
	public void testSaveAlbumByPrivilegeAndUserPrivilegeValueNotAllowedException() {
		try {
			service.saveAlbumByPrivilegeAndUser("2", "2", "4");
		} catch (PrivilegeValueNotAllowedException e) {
			assertEquals("Privilege value must be 0 (read), 1(write) or 2(both)", e.getMessage());
		}
	}
	
	@Test
	@Order(4)
	public void testSaveAlbumByPrivilegeAndUserSuccesful() {
		ResponseEntity<String> result = service.saveAlbumByPrivilegeAndUser("2", "2", "0");
		assertEquals(HttpStatus.CREATED, result.getStatusCode());
	}

	@Test
	@Order(5)
	public void testSaveAlbumByPrivilegeAndUserNullDataException() {
		try {
			service.saveAlbumByPrivilegeAndUser(null, null, null);
		} catch (NullDataException e) {
			assertEquals("Data can't be null or empty", e.getMessage());
		}
	}

	@Test
	@Order(6)
	public void testSaveAlbumByPrivilegeAndUserPrivilegeAlreadyExistsException() {
		try {
			service.saveAlbumByPrivilegeAndUser("2", "2", "0");
		} catch (PrivilegeAlreadyExistsException e) {
			assertEquals("Privilege already exists. Album: 2 , User: 2", e.getMessage());
		}
	}

	@Test
	@Order(7)
	public void testUpdateAlbumByPrivilegeAndUserSuccesful() {
		ResponseEntity<String> result = service.updateAlbumByPrivilegeAndUser("2", "2", "2");
		assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
	}

	@Test
	@Order(8)
	public void testUpdateAlbumByPrivilegeAndUserNullDataException() {
		try {
			service.updateAlbumByPrivilegeAndUser("2", null, "0");
		} catch (NullDataException e) {
			assertEquals("Data can't be null or empty", e.getMessage());
		}
	}

	@Test
	@Order(9)
	public void testUpdateAlbumByPrivilegeAndUserPrivilegeNotFoundException() {
		try {
			service.updateAlbumByPrivilegeAndUser("10", "1", "0");
		} catch (PrivilegeNotFoundException e) {
			assertEquals("Privilege not found Album: 10 , User: 1", e.getMessage());
		}
	}

	@Test
	@Order(10)
	public void testUpdateAlbumByPrivilegeAndUserPrivilegeValueNotAllowedException() {
		try {
			service.updateAlbumByPrivilegeAndUser("2", "2", "D");
		} catch (PrivilegeValueNotAllowedException e) {
			assertEquals("Privilege value must be 0 (read), 1(write) or 2(both)", e.getMessage());
		}
	}

}
