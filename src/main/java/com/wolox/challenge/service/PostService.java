package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.adapter.PostRestClient;
import com.wolox.challenge.model.Post;

@Service
public class PostService {

	PostRestClient postRestClient;

	@Autowired
	public PostService(PostRestClient postRestClient) {
		this.postRestClient = postRestClient;
	}
	
	public List<Post> getAll(){
		return postRestClient.findAllPosts();
	}
	
	public Post getAllPostsById(String postId){
		return postRestClient.findPostById(postId);
	}
	
	public List<Post> getAllPostsByUserId(String userId){
		return postRestClient.findPostsByUserId(userId); 
	}
}
