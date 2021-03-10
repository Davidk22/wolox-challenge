package com.wolox.challenge.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Photo {
	private String albumId;
	private String id;
	private String title;
	private String url;
	private String thumbnailUrl;
}
