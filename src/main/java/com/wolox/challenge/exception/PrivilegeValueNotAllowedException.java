package com.wolox.challenge.exception;

public class PrivilegeValueNotAllowedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final static String PRIVILEGE_VALUE_NOT_ALLOWED ="Privilege value must be 0 (read), 1(write) or 2(both)";
	
	public PrivilegeValueNotAllowedException () {
		super(PRIVILEGE_VALUE_NOT_ALLOWED);
	}
}
