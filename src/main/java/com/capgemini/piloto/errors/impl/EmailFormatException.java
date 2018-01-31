package com.capgemini.piloto.errors.impl;

import com.capgemini.piloto.errors.Error;

public class EmailFormatException extends Error {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getJSONError() {
		return "{\"reason\": \"Formato de e-mail incorrecto\"}";
	}

	@Override
	public String getMessageError() {
		return "Formato de e-mail incorrecto";
	}

}
