package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.PostRestClient;
import com.wolox.challenge.model.Post;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PostService {

	PostRestClient postRestClient;

	@Autowired
	public PostService(PostRestClient postRestClient) {
		this.postRestClient = postRestClient;
	}
	
	public List<Post> getAll(){
		log.debug("Consuming postRestClient - Listing all posts");
		return postRestClient.findAllPosts();
	}
	
	public Post getAllPostsById(String postId){
		log.debug("Consuming postRestClient - Listing post by id. Post:"+postId);
		return postRestClient.findPostById(postId);
	}
	
	public List<Post> getAllPostsByUserId(String userId){
		log.debug("Consuming postRestClient - Listing all posts by user. User:"+userId);
		return postRestClient.findPostsByUserId(userId); 
	}
}
