package com.capgemini.piloto.errors.impl;

import com.capgemini.piloto.errors.Error;

public class TextoFormatException extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String tipo;
	
	public TextoFormatException(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String getJSONError() {
		return "{\"reason\": \"Formato de " + tipo + " es incorrecto\"}";
	}

	@Override
	public String getMessageError() {
		return "Formato de texto " + tipo + " es incorrecto";
	}

}
