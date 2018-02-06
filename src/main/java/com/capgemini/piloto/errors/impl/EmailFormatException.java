package com.capgemini.piloto.errors.impl;


public class EmailFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Formato de e-mail incorrecto";
	}

}
