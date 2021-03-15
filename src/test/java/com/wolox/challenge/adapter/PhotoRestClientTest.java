package com.wolox.challenge.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wolox.challenge.model.Photo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PhotoRestClientTest {

	@Autowired
	PhotoRestClient photoRestClient;

	@Test
	public void testGetAllPhotos() {
		List<Photo> result = photoRestClient.findAllPhotos();
		assertEquals(5000, result.size());
	}

	@Test
	public void testFindPhotoById() {
		Photo result = photoRestClient.findPhotoById("1234");
		assertEquals("https://via.placeholder.com/600/2fb4ba", result.getUrl());
	}

	@Test
	public void testFindAllPhotosByAlbumId() {
		List<Photo> result = photoRestClient.findPhotosByAlbumId("2");
		assertEquals(50, result.size());
	}

}
