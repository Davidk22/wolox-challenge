package com.wolox.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Post {
	private String userId;
	private String id;
	private String title;
	private String body;
}
