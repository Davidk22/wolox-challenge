package com.wolox.challenge.exception;

public class PrivilegeAlreadyExistsException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final static String PRIVILEGE_ALREADY_EXISTS ="Privilege already exists. Album: %s , User: %s";
	
	public PrivilegeAlreadyExistsException (String albumId, String userId) {
		super(String.format(PRIVILEGE_ALREADY_EXISTS, albumId, userId));
	}

}
