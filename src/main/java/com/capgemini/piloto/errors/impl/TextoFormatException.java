package com.capgemini.piloto.errors.impl;

public class TextoFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String tipo;
	
	public TextoFormatException(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String getMessage() {
		return "Formato de texto de " + tipo + " es incorrecto";
	}

}
