package com.capgemini.piloto.errors.impl;


public class TelefonoFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String tipo;
	
	public TelefonoFormatException(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String getMessage() {
		return "Formato de teléfono " + tipo + " incorrecto";
	}
}
