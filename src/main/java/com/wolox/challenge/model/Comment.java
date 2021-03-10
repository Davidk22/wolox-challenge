package com.wolox.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment {
	private String postId;
	private String id;
	private String name;
	private String email;
	private String body;
}
