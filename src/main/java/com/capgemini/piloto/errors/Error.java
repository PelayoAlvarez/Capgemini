package com.capgemini.piloto.errors;

public abstract class Error extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public abstract String getJSONError();
	public abstract String getMessageError();
}
