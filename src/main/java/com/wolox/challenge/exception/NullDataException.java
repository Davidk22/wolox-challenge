package com.wolox.challenge.exception;

public class NullDataException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private final static String NULL_DATA_EXCEPTION ="Data can't be null or empty";
	
	public NullDataException() {
		super(NULL_DATA_EXCEPTION);
	}
}
