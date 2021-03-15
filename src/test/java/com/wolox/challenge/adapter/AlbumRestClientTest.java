package com.wolox.challenge.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wolox.challenge.model.Album;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlbumRestClientTest {

	@Autowired
	AlbumRestClient albumRestClient;
	
	@Test
	public void testDontFindAlbumsById() {
		Album result = albumRestClient.findAlbumsById("5");
		assertNotEquals("omnis laborum odio", result.getTitle());
	}
	
	@Test
	public void testFindAlbumsById() {
		Album result = albumRestClient.findAlbumsById("2");
		assertEquals("sunt qui excepturi placeat culpa", result.getTitle());
	}	
	
	@Test
	public void testFindAllAlbums() {
		List<Album> result = albumRestClient.findAllAlbums();
		assertEquals(100,result.size());
	}
	
	@Test
	public void testFindAlbumsByUserId() {
		List<Album> result = albumRestClient.findAlbumsByUserId("8");
		assertEquals("76", result.get(5).getId());
	} 
}
