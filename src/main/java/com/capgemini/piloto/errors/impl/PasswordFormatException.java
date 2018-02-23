package com.capgemini.piloto.errors.impl;

public class PasswordFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Formato de contraseña incorrecto";
	}
}
