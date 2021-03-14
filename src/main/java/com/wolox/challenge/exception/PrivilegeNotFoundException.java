package com.wolox.challenge.exception;


public class PrivilegeNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final static String PRIVILEGE_NOT_FOUND ="Privilege not found Album: %s , User: %s";
	public PrivilegeNotFoundException (String albumId, String userId) {
		super(String.format(PRIVILEGE_NOT_FOUND, albumId, userId));
	}
}
